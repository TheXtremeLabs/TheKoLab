package fr.lvmvrquxl.thekolab.colors.view

import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsActionState
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import kotlinx.coroutines.Runnable

internal abstract class ColorsAnimatedView(
    private val activity: AppCompatActivity,
    private val view: View
) : AnimatedView {
    companion object {
        private const val ANIMATION_ERROR_MSG: String = "No animation provided!"
    }

    protected open val exitAnimation: Runnable
        get() = this.animation
    protected open val startAnimation: Runnable
        get() = this.animation
    protected open val updateAnimation: Runnable
        get() = this.animation
    private val animation: Runnable
        get() = Runnable { throw IllegalStateException(ANIMATION_ERROR_MSG) }

    protected val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)
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

    protected fun disableClick() {
        this.view.isClickable = false
    }

    protected fun enableClick() {
        this.view.isClickable = true
    }

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