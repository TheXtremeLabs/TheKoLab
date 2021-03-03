package fr.lvmvrquxl.thekolab.splashscreen.view

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import fr.lvmvrquxl.thekolab.shared.animation.Animation
import fr.lvmvrquxl.thekolab.shared.utils.SharedColorUtils
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.splashscreen.R
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding

internal class SplashscreenView private constructor(private val activity: AppCompatActivity) :
    ActivityView<SplashscreenActivityBinding>() {
    companion object {
        private const val LOGO_IMAGE_ANIMATION_DELAY: Long = 1500
        private const val TRANSLATION_X: Float = 128f
        private val blackLogo: Int = R.drawable.kotlin_logo_black

        fun create(activity: AppCompatActivity): ActivityView<SplashscreenActivityBinding> =
            SplashscreenView(activity)
    }

    override fun bindView() {
        super.viewBinding = SplashscreenActivityBinding.inflate(this.activity.layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        this.runLogoAnimations()
    }

    private fun runAppNameAnimations() {
        this.runAppNameStartAnimation()
        this.runAppNameEndAnimation()
    }

    private fun runAppNameEndAnimation() =
        super.viewBinding?.let { binding: SplashscreenActivityBinding ->
            binding.appNameEnd.apply {
                this.alpha = 0f
                this.translationX = -TRANSLATION_X
                this.visibility = View.VISIBLE
            }
            Animation.animate(this.activity, binding.appNameEnd).apply {
                this.longDuration()
                this.translationXBy(TRANSLATION_X)
            }.run()
        }

    private fun runAppNameStartAnimation() =
        super.viewBinding?.let { binding: SplashscreenActivityBinding ->
            binding.appNameStart.apply {
                this.alpha = 0f
                this.translationX = TRANSLATION_X
                this.visibility = View.VISIBLE
            }
            Animation.animate(this.activity, binding.appNameStart).apply {
                this.longDuration()
                this.translationXBy(-TRANSLATION_X)
            }.run()
        }

    private fun runLogoAnimations() {
        this.runLogoSpinningAnimation()
        this.runLogoImageAnimation()
    }

    private fun runLogoImageAnimation() {
        super.viewBinding?.let { binding: SplashscreenActivityBinding ->
            val onStart: () -> Unit = {
                binding.logo.apply {
                    this.setImageResource(blackLogo)
                    val white: Int = SharedColorUtils.white(this@SplashscreenView.activity)
                    this.setColorFilter(white)
                }
            }
            Animation.animate(this.activity, binding.logo).apply {
                this.delay(LOGO_IMAGE_ANIMATION_DELAY)
                this.onStart(onStart)
            }.run()
        }
    }

    private fun runLogoSpinningAnimation() {
        val kf0: Keyframe = Keyframe.ofFloat(0f, 0f)
        val kf1: Keyframe = Keyframe.ofFloat(.2f, 360f * 1.5f)
        val kf2: Keyframe = Keyframe.ofFloat(.35f, 360f * 3.0f)
        val kf3: Keyframe = Keyframe.ofFloat(.4f, 360f * 5.0f)
        val kf4: Keyframe = Keyframe.ofFloat(.5f, 360f * 7.0f)
        val kf5: Keyframe = Keyframe.ofFloat(.75f, 360f * 9.0f)
        val kf6: Keyframe = Keyframe.ofFloat(1f, 360f * 10f)
        val propertyName = "rotationY"
        val propertyValuesHolder: PropertyValuesHolder =
            PropertyValuesHolder.ofKeyframe(propertyName, kf0, kf1, kf2, kf3, kf4, kf5, kf6)
        val logoAnimator: ObjectAnimator? =
            super.viewBinding?.let { binding: SplashscreenActivityBinding ->
                ObjectAnimator.ofPropertyValuesHolder(binding.logo, propertyValuesHolder)
            }
        logoAnimator?.apply {
            this.duration = 3600
            this.interpolator = LinearOutSlowInInterpolator()
            this.addListener(onEnd = { this@SplashscreenView.runAppNameAnimations() })
        }?.start()
    }
}