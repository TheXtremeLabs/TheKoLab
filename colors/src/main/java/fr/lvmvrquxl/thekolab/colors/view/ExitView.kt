package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsActionStatus
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ExitView private constructor(
    private val activity: AppCompatActivity,
    private val view: ShapeableImageView
) : AnimatedView {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 750
        private const val START_ANIMATION_DELAY: Long = 1000

        fun create(activity: AppCompatActivity, view: ShapeableImageView): LifecycleView =
            ExitView(activity, view)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)
    private var color: Color? = null

    override fun onCreate() {
        this.observeColor()
        this.observeActionStatus()
    }

    override fun onDestroy() {
        this.color = null
    }

    override fun onStart() = this.setListener()

    override fun showExitAnimation() {
        this.view.isClickable = false
        Animation.create(this.activity, this.view)
            .medium()
            .emptyAlpha()
            .delay(EXIT_ANIMATION_DELAY)
            .onEnd { this.activity.onBackPressed() }
            .start()
    }

    override fun showStartAnimation() {
        this.view.apply {
            this.alpha = 0f
            this.isClickable = false
            this@ExitView.color?.let { color: Color -> this.setColorFilter(color.value) }
        }
        Animation.create(this.activity, this.view)
            .medium()
            .delay(START_ANIMATION_DELAY)
            .onEnd { this.view.isClickable = true }
            .start()
    }

    override fun showUpdateAnimation() =
        this.viewModel.previousColor()?.let { previousColor: Color ->
            this.color?.let { color: Color ->
                ArgbAnimation.show(
                    this.view,
                    ArgbAnimation.Property.COLOR_FILTER,
                    previousColor.value,
                    color.value
                )
            }
        }

    private fun observeActionStatus() =
        this.viewModel.actionStatus.observe(this.activity) { status: ColorsActionStatus ->
            when (status) {
                ColorsActionStatus.START -> this.showStartAnimation()
                ColorsActionStatus.UPDATE -> this.showUpdateAnimation()
                ColorsActionStatus.EXIT -> this.showExitAnimation()
            }
        }

    private fun observeColor() = this.viewModel.color.observe(this.activity) { color: Color ->
        this.color = color
    }

    private fun setListener() = this.view.setOnClickListener { this.viewModel.exit() }
}