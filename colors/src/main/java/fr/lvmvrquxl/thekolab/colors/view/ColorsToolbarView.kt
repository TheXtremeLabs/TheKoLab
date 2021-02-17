package fr.lvmvrquxl.thekolab.colors.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

internal class ColorsToolbarView private constructor(
    private val activity: AppCompatActivity,
    private val toolbar: ColorsToolbarBinding,
    private val viewModel: IColorsViewModel
) : ActivityView<ColorsToolbarBinding>() {
    companion object {
        fun create(
            activity: AppCompatActivity,
            toolbar: ColorsToolbarBinding,
            viewModel: IColorsViewModel
        ) : ActivityView<ColorsToolbarBinding> = ColorsToolbarView(activity, toolbar, viewModel)
    }

    private val mediumDuration: Long
        get() = this.activity.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()

    override fun onCreate() = this.observeViewModelColor()

    override fun onStart() = this.setBackArrowListener()

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            val colorValue: Int = color.value(this.activity)
            this.setTitleColor(colorValue)
            this.setBackArrowColor(colorValue)
        }

    private fun setBackArrowColor(color: Int) {
        val previousColor: Color? = this.viewModel.previousColor()
        if (null == previousColor) {
            this.toolbar.backArrow.apply {
                this.alpha = 0f
                this.isClickable = false
                this.setColorFilter(color)
                this.animate()
                    .alpha(1f)
                    .setDuration(this@ColorsToolbarView.mediumDuration)
                    .setStartDelay(1500)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator?) {
                            this@apply.isClickable = true
                        }
                    })
                    .start()
            }
        } else ObjectAnimator.ofObject(
            this.toolbar.backArrow,
            "colorFilter",
            ArgbEvaluator(),
            previousColor.value(this.activity),
            color
        ).start()
    }

    private fun setBackArrowListener() =
        this.toolbar.backArrow.setOnClickListener { this.activity.onBackPressed() }

    private fun setTitleColor(color: Int) {
        val previousColor: Color? = this.viewModel.previousColor()
        if (null == previousColor) this.toolbar.title.apply {
            this.alpha = 0f
            this.setTextColor(color)
            this.animate()
                .alpha(1f)
                .setDuration(this@ColorsToolbarView.mediumDuration)
                .setStartDelay(500)
                .start()
        } else ObjectAnimator.ofObject(
            this.toolbar.title,
            "textColor",
            ArgbEvaluator(),
            previousColor.value(this.activity),
            color
        ).start()
    }
}