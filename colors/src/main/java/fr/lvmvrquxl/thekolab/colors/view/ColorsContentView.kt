package fr.lvmvrquxl.thekolab.colors.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

internal class ColorsContentView private constructor(
    private val activity: AppCompatActivity,
    private val content: ColorsContentBinding,
    private val viewModel: IColorsViewModel
) : ActivityView<ColorsContentBinding>() {
    companion object {
        fun create(
            activity: AppCompatActivity,
            content: ColorsContentBinding,
            viewModel: IColorsViewModel
        ): ActivityView<ColorsContentBinding> = ColorsContentView(activity, content, viewModel)
    }

    private val mediumDuration: Long
        get() = this.activity.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    private val shortDuration: Long
        get() = this.activity.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

    override fun onCreate() = this.observeViewModelColor()

    override fun onStart() = this.setChangeColorsListener()

    private fun changeColorsAnimationListener(): AnimatorListenerAdapter =
        object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                this@ColorsContentView.content.changeColors.isClickable = true
            }
        }

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            this.setColorInfo(color)
            val colorValue: Int = color.value(this.activity)
            this.setChangeColorsBackground(colorValue)
        }

    private fun onColorInfoAnimationEnd(color: Color) {
        val colorValue: Int = color.value(this.activity)
        this.content.colorName.apply {
            this.text = color.name
            this.setTextColor(colorValue)
            this.animate()
                .alpha(1f)
                .setDuration(this@ColorsContentView.mediumDuration)
                .start()
        }
    }

    private fun setChangeColorsBackground(color: Int) {
        when (val previousColor: Color? = this.viewModel.previousColor()) {
            null -> this.content.changeColors.apply {
                this.alpha = 0f
                this.isClickable = false
                this.setBackgroundColor(color)
                this.animate()
                    .alpha(1f)
                    .setDuration(this@ColorsContentView.mediumDuration)
                    .setStartDelay(1000)
                    .setListener(this@ColorsContentView.changeColorsAnimationListener())
                    .start()
            }
            else -> ObjectAnimator.ofObject(
                this.content.changeColors,
                "backgroundColor",
                ArgbEvaluator(),
                previousColor.value(this.activity),
                color
            ).start()
        }
    }

    private fun setChangeColorsListener() =
        this.content.changeColors.setOnClickListener { this.viewModel.updateColor() }

    private fun setColorInfo(color: Color) {
        this.content.colorName.apply {
            this.animate()
                .alpha(0f)
                .setDuration(this@ColorsContentView.shortDuration)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) =
                        this@ColorsContentView.onColorInfoAnimationEnd(color)
                })
                .start()
        }
    }
}