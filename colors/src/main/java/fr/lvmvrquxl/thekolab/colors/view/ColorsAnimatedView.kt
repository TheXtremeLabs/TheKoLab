package fr.lvmvrquxl.thekolab.colors.view

import android.view.View
import androidx.annotation.CallSuper
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsStateManager
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsViewModel
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.animation.Animation
import fr.lvmvrquxl.thekolab.shared.animation.ArgbAnimation
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.shared.viewmodel.StateManager

/**
 * Parent of all animated views in the colors activity.
 *
 * @since 1.0.0
 */
internal abstract class ColorsAnimatedView(private val activity: Activity, private val view: View) :
    AnimatedView(activity, view) {
    /**
     * ARGB animation instance for the view.
     *
     * @since 1.0.0
     */
    protected val argbAnimation: ArgbAnimation
        get() = ArgbAnimation.animate(this.view)

    /**
     * Animation instance with a medium duration, corresponding to 400 milliseconds.
     *
     * @since 1.0.0
     */
    protected val mediumAnimation: Animation
        get() = super.animation.apply { this.mediumDuration() }

    /**
     * Current color to display.
     *
     * @since 1.0.0
     */
    protected var color: Color? = null

    /**
     * View model's instance of the activity.
     *
     * @since 1.0.0
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
     *
     * @since 1.0.0
     */
    protected fun disableClick() {
        this.view.isClickable = false
    }

    /**
     * Enable click on the current view.
     *
     * @since 1.0.0
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

    private fun observeViewModelColor() =
        this.viewModel?.color?.observe(this.activity) { color: Color -> this.color = color }

    private fun observeViewModelState() =
        this.viewModel?.state?.observe(this.activity) { state: String ->
            when (state) {
                ColorsStateManager.CHANGE_COLORS -> super.showUpdateAnimation()
                ColorsStateManager.EXIT -> super.showExitAnimation()
                StateManager.RESUME -> super.showStartAnimation()
            }
        }
}