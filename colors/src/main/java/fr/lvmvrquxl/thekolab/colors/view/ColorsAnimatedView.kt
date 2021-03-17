package fr.lvmvrquxl.thekolab.colors.view

import android.view.View
import androidx.annotation.CallSuper
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsStateManager
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsViewModel
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.animation.Animation
import fr.lvmvrquxl.thekolab.shared.animation.ArgbAnimation
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.shared.viewmodel.StateManager

/**
 * Parent of all animated views in the colors activity.
 *
 * @param activityReference Reference of the colors activity
 * @param view Current view
 */
internal abstract class ColorsAnimatedView(
    private val activityReference: ActivityReference,
    private val view: View
) : AnimatedView(activityReference, view) {
    /**
     * ARGB animation instance for the view.
     */
    protected val argbAnimation: ArgbAnimation
        get() = ArgbAnimation.animate(this.view)

    /**
     * Animation instance with a medium duration, corresponding to 400 milliseconds.
     */
    protected val mediumAnimation: Animation
        get() = super.animation.apply { this.mediumDuration() }

    /**
     * Current color to display.
     */
    protected var color: Color? = null

    /**
     * View model's instance of the activity.
     */
    protected var viewModel: ColorsViewModel? = null

    @CallSuper
    override fun onCreate() {
        this.initViewModel()
        this.observeViewModelColor()
        this.observeViewModelState()
    }

    override fun onDestroy() {
        this.destroyColor()
        this.destroyViewModel()
        super.onDestroy()
    }

    @CallSuper
    override fun onStart() = super.hide()

    /**
     * Disable click on the current view.
     */
    protected fun disableClick() {
        this.view.isClickable = false
    }

    /**
     * Enable click on the current view.
     */
    protected fun enableClick() {
        this.view.isClickable = true
    }

    private fun destroyColor() {
        this.color = null
    }

    private fun destroyViewModel() {
        this.viewModel = null
    }

    private fun initViewModel() {
        this.viewModel = ColorsViewModel.instance
    }

    private fun observeViewModelColor() = this.activityReference.get()?.let { activity: Activity ->
        this.viewModel?.color?.observe(activity) { color: Color -> this.color = color }
    }

    private fun observeViewModelState() {
        this.activityReference.get()?.let { activity: Activity ->
            this.viewModel?.state?.observe(activity) { state: String ->
                when (state) {
                    ColorsStateManager.CHANGE_COLORS -> super.showUpdateAnimation()
                    ColorsStateManager.EXIT -> super.showExitAnimation()
                    StateManager.RESUME -> super.showStartAnimation()
                }
            }
        }
    }
}