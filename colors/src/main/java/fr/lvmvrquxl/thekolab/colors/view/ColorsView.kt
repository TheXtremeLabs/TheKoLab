package fr.lvmvrquxl.thekolab.colors.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.view.View
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

internal class ColorsView private constructor(private val activity: Activity) :
    ActivityView<ColorsActivityBinding>() {
    companion object {
        fun create(activity: Activity): ActivityView<ColorsActivityBinding> = ColorsView(activity)
    }

    private val colors: List<Color> = listOf(
        Color(StringUtils.white(this.activity)) { context: Context -> ColorUtils.white(context) },
        Color(StringUtils.orange(this.activity)) { context: Context -> ColorUtils.orange(context) },
        Color(StringUtils.purple(this.activity)) { context: Context -> ColorUtils.purple(context) },
        Color(StringUtils.blue(this.activity)) { context: Context -> ColorUtils.blue(context) },
        Color(StringUtils.red(this.activity)) { context: Context -> ColorUtils.red(context) }
    )

    private var previousColor: Color = this.colors.first()

    override fun onCreate() = this.bindViews()

    override fun onStart() {
        super.viewBinding?.colorsToolbar?.backArrow?.setOnClickListener { this.activity.onBackPressed() }
        super.viewBinding?.let { binding: ColorsActivityBinding ->
            val currentColor: Int = this.previousColor.getColor(this@ColorsView.activity)
            binding.colorsContent.colorName.apply {
                this.text = this@ColorsView.previousColor.name
                this.setTextColor(currentColor)
            }
            binding.colorsContent.changeColors.setBackgroundColor(currentColor)
            binding.colorsContent.changeColors.setOnClickListener { view: View ->
                val shortDuration: Int =
                    this.activity.resources.getInteger(android.R.integer.config_shortAnimTime)
                val animators: MutableList<ObjectAnimator> = mutableListOf()
                var color: Color = this.colors.random()
                while (this.previousColor == color) color = this.colors.random()
                val previousBackgroundColor: Int = previousColor.getColor(this.activity)
                this.previousColor = color
                val backgroundColor: Int = color.getColor(this.activity)
                view.setBackgroundColor(backgroundColor)
                animators.add(
                    ObjectAnimator.ofObject(
                        view,
                        "backgroundColor",
                        ArgbEvaluator(),
                        previousBackgroundColor,
                        backgroundColor
                    )
                )
                animators.add(
                    ObjectAnimator.ofObject(
                        binding.colorsToolbar.title,
                        "textColor",
                        ArgbEvaluator(),
                        previousBackgroundColor,
                        backgroundColor
                    )
                )
                animators.add(
                    ObjectAnimator.ofObject(
                        binding.colorsToolbar.backArrow,
                        "colorFilter",
                        ArgbEvaluator(),
                        previousBackgroundColor,
                        backgroundColor
                    )
                )
                animators.forEach { it.setDuration(shortDuration.toLong()).start() }
                val mediumDuration: Int =
                    this.activity.resources.getInteger(android.R.integer.config_mediumAnimTime)
                binding.colorsContent.colorName.apply {
                    this.animate()
                        .alpha(0f)
                        .setDuration(shortDuration.toLong())
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                this@apply.text = color.name
                                this@apply.setTextColor(backgroundColor)
                                this@apply.animate()
                                    .alpha(1f)
                                    .setDuration(mediumDuration.toLong())
                                    .start()
                            }
                        })
                        .start()
                }
            }
        }
    }

    private fun bindViews() {
        super.viewBinding = ColorsActivityBinding.inflate(this.activity.layoutInflater)
    }
}