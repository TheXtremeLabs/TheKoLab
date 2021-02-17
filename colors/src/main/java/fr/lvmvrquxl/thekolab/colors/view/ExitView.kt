package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ExitView private constructor(
    private val activity: AppCompatActivity,
    private val view: ShapeableImageView
) : AnimatedView() {
    companion object {
        private const val ONE_SECOND_AND_A_HALF: Long = 1500

        fun create(activity: AppCompatActivity, view: ShapeableImageView): LifecycleView =
            ExitView(activity, view)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)
    private var color: Color? = null

    override fun onCreate() = this.observeViewModelColor()

    override fun onDestroy() {
        this.color = null
    }

    override fun onStart() = this.setListener()

    override fun showEntryAnimation() {
        this.view.apply {
            this.alpha = 0f
            this.isClickable = false
            this@ExitView.color?.let { color: Color -> this.setColorFilter(color.value) }
        }
        Animation.create(this.activity, this.view)
            .medium()
            .delay(ONE_SECOND_AND_A_HALF)
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

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            this.color = color
            when (this.viewModel.previousColor()) {
                null -> this.showEntryAnimation()
                else -> this.showUpdateAnimation()
            }
        }

    private fun setListener() = this.view.setOnClickListener { this.activity.onBackPressed() }
}