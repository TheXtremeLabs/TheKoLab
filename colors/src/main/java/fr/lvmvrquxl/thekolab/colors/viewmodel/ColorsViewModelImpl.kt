package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * The view model's implementation of the colors activity.
 */
internal class ColorsViewModelImpl private constructor() : ColorsViewModel() {
    companion object {
        /**
         * Create a new instance of the view model's implementation.
         *
         * @return New instance of the view model's implementation.
         */
        fun create(): ColorsViewModel = ColorsViewModelImpl()
    }

    override val color: LiveData<Color>?
        get() = this.colorViewModel?.currentColor

    override val previousColor: Color?
        get() = this.colorViewModel?.previousColor

    override val state: LiveData<String>?
        get() = this.stateViewModel?.currentState

    private var activityReference: ActivityReference? = null
    private var colorViewModel: ColorsColorViewModel? = null
    private var stateViewModel: ColorsStateViewModel? = null

    override fun changeColors() {
        this.colorViewModel?.changeColors()
        this.stateViewModel?.changeColors()
    }

    override fun closeActivity() {
        this.stateViewModel?.close()
    }

    override fun observe(activityReference: ActivityReference) {
        this.activityReference =
            activityReference.apply { this.get()?.addObserver(this@ColorsViewModelImpl) }
        this.initColorViewModel()
        this.initStateViewModel()
    }

    override fun onBackPressed() {
        if (false == this.stateViewModel?.isExiting()) this.stateViewModel?.exit()
    }

    override fun onCleared() {
        this.clearActivityReference()
        this.clearColorViewModel()
        this.clearStateViewModel()
    }

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    private fun clearActivityReference() {
        this.activityReference = null
    }

    private fun clearColorViewModel() {
        this.colorViewModel = null
    }

    private fun clearStateViewModel() {
        this.stateViewModel = null
    }

    private fun initColorViewModel() {
        this.colorViewModel = this.activityReference?.let { reference: ActivityReference ->
            ColorsColorViewModel.observe(reference)
        }
    }

    private fun initStateViewModel() {
        this.stateViewModel = this.activityReference?.let { reference: ActivityReference ->
            ColorsStateViewModel.observe(reference)
        }
    }

    private fun stopActivityObservation() = this.activityReference?.get()?.removeObserver(this)
}