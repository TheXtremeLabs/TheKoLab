package fr.lvmvrquxl.thekolab.colors.view

import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.utils.animation.Animation
import fr.lvmvrquxl.thekolab.colors.utils.animation.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsActionState
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import kotlinx.coroutines.Runnable

/**
 * Parent of all animated views in the colors activity.
 *
 * @param activity Instance of the colors activity
 * @param view View to animate
 *
 * @since 1.0.0
 *
 * @see AnimatedView
 * @see AppCompatActivity
 * @see View
 */
internal abstract class ColorsAnimatedView(
    private val activity: AppCompatActivity,
    private val view: View
) : AnimatedView {
    /**
     * Animation instance for the view.
     *
     * @since 1.0.0
     *
     * @see Animation
     */
    protected val animation: Animation
        get() = Animation.animate(this.activity, this.view)

    /**
     * ARGB animation instance for the view.
     *
     * @since 1.0.0
     *
     * @see ArgbAnimation
     */
    protected val argbAnimation: ArgbAnimation
        get() = ArgbAnimation.animate(this.view)

    /**
     * Animation executed when the user closes the activity.
     *
     * @since 1.0.0
     *
     * @see Runnable
     */
    protected open val exitAnimation: Runnable
        get() = this.animation

    /**
     * Animation instance with a medium duration, corresponding to 400 milliseconds.
     *
     * @since 1.0.0
     *
     * @see Animation
     */
    protected val mediumAnimation: Animation
        get() = this.animation.apply { this.medium() }

    /**
     * Animation executed when the activity starts.
     *
     * @since 1.0.0
     *
     * @see Runnable
     */
    protected open val startAnimation: Runnable
        get() = this.animation

    /**
     * Animation executed when displayed data is updated.
     *
     * @since 1.0.0
     *
     * @see Runnable
     */
    protected open val updateAnimation: Runnable
        get() = this.animation

    /**
     * View model's instance of the activity.
     *
     * @since 1.0.0
     *
     * @see IColorsViewModel
     */
    protected val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)

    /**
     * Current color to display.
     *
     * @since 1.0.0
     *
     * @see Color
     */
    protected var color: Color? = null

    override fun onCreate() {
        this.observeColor()
        this.observeActionState()
    }

    override fun onDestroy() {
        this.color = null
    }

    @CallSuper
    override fun showExitAnimation() = this.exitAnimation.run()

    @CallSuper
    override fun showStartAnimation() = this.startAnimation.run()

    @CallSuper
    override fun showUpdateAnimation() = this.updateAnimation.run()

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

    /**
     * Hide the current view.
     *
     * @since 1.0.0
     */
    protected fun hide() {
        this.view.alpha = 0f
    }

    private fun observeActionState() =
        this.viewModel.actionState.observe(this.activity) { state: ColorsActionState ->
            when (state) {
                ColorsActionState.START -> this.showStartAnimation()
                ColorsActionState.UPDATE -> this.showUpdateAnimation()
                ColorsActionState.EXIT -> this.showExitAnimation()
                else -> {
                }
            }
        }

    private fun observeColor() = this.viewModel.color.observe(this.activity) { color: Color ->
        this.color = color
    }
}