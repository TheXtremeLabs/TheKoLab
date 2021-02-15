package fr.lvmvrquxl.thekolab.colors.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Activity
import android.view.View
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.presenter.ColorsPresenter

internal class ColorsClickListener private constructor(
    private val presenter: ColorsPresenter,
    private val binding: ColorsActivityBinding,
    private val activity: Activity
) : View.OnClickListener {
    companion object {
        fun create(
            presenter: ColorsPresenter,
            binding: ColorsActivityBinding,
            activity: Activity
        ): View.OnClickListener = ColorsClickListener(presenter, binding, activity)
    }

    override fun onClick(view: View?) {
        val nextColor: Int = this.presenter.randomizeNextColor().nextColor()
        val nextName: String = this.presenter.nextColorName()
        this.setChangeColorsBackground(nextColor)
        this.setTitleColor(nextColor)
        this.setBackArrowColor(nextColor)
        this.setColorName(nextColor, nextName)
        this.presenter.updateCurrentColor()
    }

    private fun getMediumDuration() =
        this.activity.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()

    private fun getShortDuration() =
        this.activity.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

    private fun setBackArrowColor(color: Int) = ObjectAnimator.ofObject(
        this.binding.colorsToolbar.backArrow,
        "colorFilter",
        ArgbEvaluator(),
        this.presenter.currentColor(),
        color
    ).start()

    private fun setChangeColorsBackground(color: Int) = ObjectAnimator.ofObject(
        this.binding.colorsContent.changeColors,
        "backgroundColor",
        ArgbEvaluator(),
        this.presenter.currentColor(),
        color
    ).start()

    private fun setColorName(color: Int, colorName: String) {
        val mediumDuration: Long = this.getMediumDuration()
        val shortDuration: Long = this.getShortDuration()
        this.binding.colorsContent.colorName.apply {
            this.animate()
                .alpha(0f)
                .setDuration(shortDuration)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        this@apply.text = colorName
                        this@apply.setTextColor(color)
                        this@apply.animate()
                            .alpha(1f)
                            .setDuration(mediumDuration)
                            .start()
                    }
                })
                .start()
        }
    }

    private fun setTitleColor(color: Int) = ObjectAnimator.ofObject(
        this.binding.colorsToolbar.title,
        "textColor",
        ArgbEvaluator(),
        this.presenter.currentColor(),
        color
    ).start()
}