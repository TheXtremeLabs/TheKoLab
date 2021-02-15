package fr.lvmvrquxl.thekolab.colors.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

internal class ColorsView private constructor(private val activity: AppCompatActivity) :
    ActivityView<ColorsActivityBinding>() {
    companion object {
        fun create(activity: AppCompatActivity): ActivityView<ColorsActivityBinding> =
            ColorsView(activity)
    }

    private val viewModel: ColorsViewModel = ColorsViewModel.create(this.activity)
    private var content: ColorsContentBinding? = null
    private var toolbar: ColorsToolbarBinding? = null

    override fun onCreate() {
        this.bindViews()
        this.observeViewModelColor()
    }

    override fun onStart() {
        this.setBackArrowListener()
        this.setChangeColorsListener()
        this.viewModel.onStart()
    }

    private fun bindContent() {
        this.content = super.viewBinding?.colorsContent
    }

    private fun bindToolbar() {
        this.toolbar = super.viewBinding?.colorsToolbar
    }

    private fun bindViews() {
        this.inflateViewBinding()
        this.bindContent()
        this.bindToolbar()
    }

    private fun getMediumDuration() =
        this.activity.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()

    private fun getShortDuration() =
        this.activity.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

    private fun inflateViewBinding() {
        super.viewBinding = ColorsActivityBinding.inflate(this.activity.layoutInflater)
    }

    private fun observeViewModelColor() =
        this.viewModel.color().observe(this.activity) { color: Color ->
            this.setColorInfo(color)
            val colorValue: Int = color.value(this.activity)
            this.setTitleColor(colorValue)
            this.setChangeColorsBackground(colorValue)
            this.setBackArrowColor(colorValue)
        }

    private fun onColorInfoAnimationEnd(color: Color) {
        val colorValue: Int = color.value(this.activity)
        val mediumDuration: Long = this.getMediumDuration()
        this.content?.let { content: ColorsContentBinding ->
            content.colorName.apply {
                this.text = color.name
                this.setTextColor(colorValue)
                this.animate()
                    .alpha(1f)
                    .setDuration(mediumDuration)
                    .start()
            }
        }
    }

    private fun setBackArrowColor(color: Int) {
        val previousColor: Color? = this.viewModel.previousColor()
        if (null == previousColor) {
            val duration: Long = this.getMediumDuration()
            this.toolbar?.backArrow?.apply {
                this.alpha = 0f
                this.isClickable = false
                this.setColorFilter(color)
                this.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setStartDelay(1500)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationStart(animation: Animator?) {
                            this@apply.isClickable = true
                        }
                    })
                    .start()
            }
        } else ObjectAnimator.ofObject(
            this.toolbar?.backArrow,
            "colorFilter",
            ArgbEvaluator(),
            previousColor.value(this.activity),
            color
        ).start()
    }

    private fun setBackArrowListener() = this.toolbar?.let { toolbar: ColorsToolbarBinding ->
        toolbar.backArrow.setOnClickListener { this.activity.onBackPressed() }
    }

    private fun setColorInfo(color: Color) {
        val shortDuration: Long = this.getShortDuration()
        this.content?.let { content: ColorsContentBinding ->
            content.colorName.apply {
                this.animate()
                    .alpha(0f)
                    .setDuration(shortDuration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) =
                            this@ColorsView.onColorInfoAnimationEnd(color)
                    })
                    .start()
            }
        }
    }

    private fun setChangeColorsBackground(color: Int) {
        val previousColor: Color? = this.viewModel.previousColor()
        if (null == previousColor) {
            val duration: Long = this.getMediumDuration()
            this.content?.changeColors?.apply {
                this.alpha = 0f
                this.isClickable = false
                this.setBackgroundColor(color)
                this.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setStartDelay(1000)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            this@apply.isClickable = true
                        }
                    })
                    .start()
            }
        } else ObjectAnimator.ofObject(
            this.content?.changeColors,
            "backgroundColor",
            ArgbEvaluator(),
            previousColor.value(this.activity),
            color
        ).start()
    }

    private fun setChangeColorsListener() {
        this.content?.changeColors?.setOnClickListener { this.viewModel.updateColor() }
    }

    private fun setTitleColor(color: Int) {
        val previousColor: Color? = this.viewModel.previousColor()
        if (null == previousColor) {
            val duration: Long = this.getMediumDuration()
            this.toolbar?.title?.apply {
                this.alpha = 0f
                this.setTextColor(color)
                this.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setStartDelay(500)
                    .start()
            }
        } else ObjectAnimator.ofObject(
            this.toolbar?.title,
            "textColor",
            ArgbEvaluator(),
            previousColor.value(this.activity),
            color
        ).start()
    }
}