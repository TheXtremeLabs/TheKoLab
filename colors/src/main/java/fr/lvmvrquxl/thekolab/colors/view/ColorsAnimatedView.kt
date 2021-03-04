package fr.lvmvrquxl.thekolab.colors.view

import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsState
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsViewModel
import fr.lvmvrquxl.thekolab.shared.animation.Animation
import fr.lvmvrquxl.thekolab.shared.animation.ArgbAnimation
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView

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
) : AnimatedView(activity, view) {
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
     * Animation instance with a medium duration, corresponding to 400 milliseconds.
     *
     * @since 1.0.0
     *
     * @see Animation
     */
    protected val mediumAnimation: Animation
        get() = super.animation.apply { this.mediumDuration() }

    private val colorObserver: Observer<Color>
        get() = Observer { color: Color -> this.color = color }

    private val stateObserver: Observer<ColorsState>
        get() = Observer { state: ColorsState ->
            when (state) {
                ColorsState.EXIT -> super.showExitAnimation()
                ColorsState.RESUME -> super.showStartAnimation()
                ColorsState.UPDATE -> super.showUpdateAnimation()
                else -> {
                }
            }
        }

    /**
     * View model's instance of the activity.
     *
     * @since 1.0.0
     *
     * @see ColorsViewModel
     */
    protected val viewModel: ColorsViewModel
        get() = ColorsViewModel.instance(this.activity)

    /**
     * Current color to display.
     *
     * @since 1.0.0
     *
     * @see Color
     */
    protected var color: Color? = null

    override fun observeViewModel() {
        this.viewModel.color.observe(this.activity, this.colorObserver)
        this.viewModel.state.observe(this.activity, this.stateObserver)
    }

    override fun onDestroy() {
        this.color = null
        super.onDestroy()
    }

    @CallSuper
    override fun onStart() {
        super.hide()
    }

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
}