package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.activity.Activity
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

    override val color: LiveData<Color>?
        get() = this.colorManager?.currentColor

    override val previousColor: Color?
        get() = this.colorManager?.previousColor

    override val state: LiveData<String>?
        get() = this.stateManager?.currentState

    private var activityReference: WeakReference<Activity>? = null
    private var colorManager: ColorsColorManager? = null
    private var stateManager: ColorsStateManager? = null

    override fun changeColors() {
        this.colorManager?.changeColors()
        this.stateManager?.changeColors()
    }

    override fun closeActivity() {
        this.stateManager?.close()
    }

    override fun observe(activity: Activity) = activity.apply {
        this@ColorsViewModelImpl.initActivityReference(this)
        this@ColorsViewModelImpl.initColorManager(this)
        this@ColorsViewModelImpl.initStateManager(this)
    }.addObserver(this)

    override fun onBackPressed() {
        if (false == this.stateManager?.isExiting()) this.stateManager?.exit()
    }

    override fun onCleared() {
        this.activityReference = null
        this.colorManager = null
        this.stateManager = null
    }

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    private fun initActivityReference(activity: Activity) {
        this.activityReference = WeakReference(activity)
    }

    private fun initColorManager(activity: Activity) {
        this.colorManager = ColorsColorManager.observe(activity)
    }

    private fun initStateManager(activity: Activity) {
        this.stateManager = ColorsStateManager.observe(activity)
    }

    private fun stopActivityObservation() = this.activityReference?.get()?.removeObserver(this)
}