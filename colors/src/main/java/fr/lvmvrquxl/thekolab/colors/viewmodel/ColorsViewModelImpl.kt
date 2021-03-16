package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.repository.IColorsRepository
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.lang.ref.WeakReference

/**
 * The view model's implementation of the colors activity.
 *
 * @since 1.0.0
 */
internal class ColorsViewModelImpl private constructor() : ColorsViewModel() {
    companion object {
        /**
         * Create a new instance of the view model's implementation.
         *
         * @return New instance of the view model's implementation.
         *
         * @since 2.0.0
         */
        fun create(): ColorsViewModel = ColorsViewModelImpl()
    }

    override val color: LiveData<Color>
        get() = this.colorData

    override val previousColor: Color?
        get() = this.previousColorData

    override val state: LiveData<String>?
        get() = this.stateManager?.currentState

    private val colorData: MutableLiveData<Color> = MutableLiveData()

    private var activityReference: WeakReference<Activity>? = null
    private var currentColor: Color? = null
    private var previousColorData: Color? = null
    private var repository: IColorsRepository? = null
    private var stateManager: ColorsStateManager? = null

    override fun changeColors() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.syncColor()
        this.stateManager?.changeColors()
    }

    override fun closeActivity() {
        this.stateManager?.close()
    }

    override fun observe(activity: Activity) = activity.apply {
        this@ColorsViewModelImpl.initActivityReference(this)
        this@ColorsViewModelImpl.initStateManager(this)
    }.addObserver(this)

    override fun onBackPressed() {
        if (false == this.stateManager?.isExiting()) this.stateManager?.exit()
    }

    override fun onCleared() {
        this.activityReference = null
        this.currentColor = null
        this.previousColorData = null
        this.repository = null
        this.stateManager = null
    }

    override fun onCreate() {
        this.initRepository()
    }

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    override fun onStart() {
        this.currentColor = this.repository?.firstColor
        this.syncColor()
    }

    override fun onStop() = this.backupColor()

    private fun backupColor() {
        runBlocking(Dispatchers.Default) {
            this@ColorsViewModelImpl.currentColor?.let { color: Color ->
                this@ColorsViewModelImpl.repository?.backupColor(color)
            }
        }
    }

    private fun initActivityReference(activity: Activity) {
        this.activityReference = WeakReference(activity)
    }

    private fun initRepository() = this.activityReference?.get()?.let { activity: Activity ->
        this.repository = IColorsRepository.instance(activity)
    }

    private fun initStateManager(activity: Activity) {
        this.stateManager = ColorsStateManager.observe(activity)
    }

    private fun pickRandomColor(): Color? = runBlocking(Dispatchers.Default) {
        var color: Color? = this@ColorsViewModelImpl.repository?.randomColor
        while (this@ColorsViewModelImpl.currentColor == color)
            color = this@ColorsViewModelImpl.repository?.randomColor
        color
    }

    private fun stopActivityObservation() = this.activityReference?.get()?.removeObserver(this)

    private fun syncColor() =
        this.currentColor?.let { color: Color -> this.colorData.value = color }

    private fun updateCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.currentColor = this@ColorsViewModelImpl.pickRandomColor()
    }

    private fun updatePreviousColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.previousColorData = this@ColorsViewModelImpl.currentColor
    }
}