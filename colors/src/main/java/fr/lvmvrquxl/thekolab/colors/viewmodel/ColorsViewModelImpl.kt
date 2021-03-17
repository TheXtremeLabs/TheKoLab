package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * The view model's implementation of the colors activity.
 *
 * @since 2.0.0
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

    override val color: LiveData<Color>?
        get() = this.colorManager?.currentColor

    override val previousColor: Color?
        get() = this.colorManager?.previousColor

    override val state: LiveData<String>?
        get() = this.stateManager?.currentState

    private var activityReference: ActivityReference? = null
    private var colorManager: ColorsColorManager? = null
    private var stateManager: ColorsStateManager? = null

    override fun changeColors() {
        this.colorManager?.changeColors()
        this.stateManager?.changeColors()
    }

    override fun closeActivity() {
        this.stateManager?.close()
    }

    override fun observe(activityReference: ActivityReference) {
        this.activityReference =
            activityReference.apply { this.get()?.addObserver(this@ColorsViewModelImpl) }
        this.initColorManager()
        this.initStateManager()
    }

    override fun onBackPressed() {
        if (false == this.stateManager?.isExiting()) this.stateManager?.exit()
    }

    override fun onCleared() {
        this.clearActivityReference()
        this.clearColorManager()
        this.clearStateManager()
    }

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    private fun clearActivityReference() {
        this.activityReference = null
    }

    private fun clearColorManager() {
        this.colorManager = null
    }

    private fun clearStateManager() {
        this.stateManager = null
    }

    private fun initColorManager() {
        this.colorManager = this.activityReference?.let { reference: ActivityReference ->
            ColorsColorManager.observe(reference)
        }
    }

    private fun initStateManager() {
        this.stateManager = this.activityReference?.let { reference: ActivityReference ->
            ColorsStateManager.observe(reference)
        }
    }

    private fun stopActivityObservation() = this.activityReference?.get()?.removeObserver(this)
}