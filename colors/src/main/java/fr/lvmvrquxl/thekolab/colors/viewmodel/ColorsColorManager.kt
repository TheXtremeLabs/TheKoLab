package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.repository.ColorsRepository
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * Manager of the color displayed in the colors activity.
 *
 * @since 2.0.0
 */
internal class ColorsColorManager private constructor(
    private val activityReference: ActivityReference
) : LifecycleView() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the activity to observe
         *
         * @return New instance of the manager
         *
         * @since 2.0.0
         */
        fun observe(activityReference: ActivityReference): ColorsColorManager =
            ColorsColorManager(activityReference).apply {
                activityReference.get()?.addObserver(this)
            }
    }

    /**
     * Current color displayed in the activity.
     *
     * @since 2.0.0
     */
    val currentColor: LiveData<Color>
        get() = this.currentColorData

    /**
     * Previous color that was displayed in the activity.
     *
     * @since 2.0.0
     */
    val previousColor: Color?
        get() = this.previousColorValue

    private val currentColorData: MutableLiveData<Color> = MutableLiveData()

    private var currentColorValue: Color? = null
    private var previousColorValue: Color? = null
    private var repository: ColorsRepository? = null

    override fun onCreate() = this.initRepository()

    override fun onDestroy() {
        this.destroyCurrentColor()
        this.destroyPreviousColor()
        this.destroyRepository()
        this.stopActivityObservation()
    }

    override fun onStart() {
        this.initCurrentColor()
        this.syncCurrentColor()
    }

    override fun onStop() {
        this.backupCurrentColor()
    }

    /**
     * Change colors to be displayed in the activity.
     *
     * @since 2.0.0
     */
    fun changeColors() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.syncCurrentColor()
    }

    private fun backupCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsColorManager.currentColorValue?.let { color: Color ->
            this@ColorsColorManager.repository?.backupColor(color)
        }
    }

    private fun destroyCurrentColor() {
        this.currentColorValue = null
    }

    private fun destroyPreviousColor() {
        this.previousColorValue = null
    }

    private fun destroyRepository() {
        this.repository = null
    }

    private fun initCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsColorManager.currentColorValue = this@ColorsColorManager.repository?.firstColor
    }

    private fun initRepository() = runBlocking(Dispatchers.Default) {
        this@ColorsColorManager.repository =
            ColorsRepository.instance(this@ColorsColorManager.activityReference)
    }

    private fun pickRandomColor(): Color? = runBlocking(Dispatchers.Default) {
        var color: Color? = this@ColorsColorManager.repository?.randomColor
        while (this@ColorsColorManager.currentColorValue == color)
            color = this@ColorsColorManager.repository?.randomColor
        color
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
        this@ColorsColorManager.previousColorValue = this@ColorsColorManager.currentColorValue
    }
}