package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.repository.ColorsRepository
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * View model of the color displayed in the colors activity.
 */
internal class ColorsColorViewModel private constructor(
    private val activityReference: ActivityReference
) : ViewModel() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the activity to observe
         *
         * @return New instance of the view model
         */
        fun observe(activityReference: ActivityReference): ColorsColorViewModel =
            ColorsColorViewModel(activityReference).apply {
                activityReference.get()?.addObserver(this)
            }
    }

    /**
     * Current color displayed in the activity.
     */
    val currentColor: LiveData<Color>
        get() = this.currentColorData

    /**
     * Previous color that was displayed in the activity.
     */
    val previousColor: Color?
        get() = this.previousColorValue

    private val currentColorData: MutableLiveData<Color> = MutableLiveData()

    private var currentColorValue: Color? = null
    private var previousColorValue: Color? = null
    private var repository: ColorsRepository? = null

    override fun onCleared() {
        this.clearCurrentColor()
        this.clearPreviousColor()
        this.clearRepository()
    }

    override fun onCreate() = this.initRepository()

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    override fun onStart() {
        this.initCurrentColor()
        this.syncCurrentColor()
    }

    override fun onStop() = this.backupCurrentColor()

    /**
     * Change colors to be displayed in the activity.
     */
    fun changeColors() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.syncCurrentColor()
    }

    private fun clearCurrentColor() {
        this.currentColorValue = null
    }

    private fun clearPreviousColor() {
        this.previousColorValue = null
    }

    private fun clearRepository() {
        this.repository = null
    }

    private fun backupCurrentColor() {
        this.currentColorValue?.let { color: Color ->
            runBlocking(Dispatchers.Default) {
                this@ColorsColorViewModel.repository?.backupColor(color)
            }
        }
    }

    private fun initCurrentColor() {
        this.currentColorValue =
            runBlocking(Dispatchers.Default) { this@ColorsColorViewModel.repository?.firstColor() }
    }

    private fun initRepository() {
        this.repository = runBlocking(Dispatchers.Default) {
            ColorsRepository.instance(this@ColorsColorViewModel.activityReference)
        }
    }

    private fun pickRandomColor(): Color {
        var color: Color? = null
        while (null == color || this.currentColorValue == color)
            color = runBlocking(Dispatchers.Default) {
                this@ColorsColorViewModel.repository?.randomColor()
            }
        return color
    }

    private fun stopActivityObservation() {
        this.activityReference.get()?.removeObserver(this)
    }

    private fun syncCurrentColor() =
        this.currentColorValue?.let { color: Color -> this.currentColorData.value = color }

    private fun updateCurrentColor() {
        this.currentColorValue = this.pickRandomColor()
    }

    private fun updatePreviousColor() {
        this.previousColorValue = this.currentColorValue
    }
}