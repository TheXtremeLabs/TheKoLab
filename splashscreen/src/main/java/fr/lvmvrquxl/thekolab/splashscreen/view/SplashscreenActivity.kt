package fr.lvmvrquxl.thekolab.splashscreen.view

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import fr.lvmvrquxl.thekolab.shared.utils.SharedColorUtils
import fr.lvmvrquxl.thekolab.splashscreen.R
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding

class SplashscreenActivity : AppCompatActivity() {
    companion object {
        private const val TRANSLATION_X: Float = 128f
        private const val LOGO_FADE_ANIMATION_DELAY: Long = 1500
    }

    private val animationDuration: Long
        get() = this.resources.getInteger(android.R.integer.config_longAnimTime).toLong()

    private var viewBinding: SplashscreenActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bindView()
        this.setContentView()
    }

    override fun onStart() {
        super.onStart()
        this.runLogoAnimations()
    }

    private fun bindView() {
        this.viewBinding = SplashscreenActivityBinding.inflate(this.layoutInflater)
    }

    private fun setContentView() = this.viewBinding?.let { binding: SplashscreenActivityBinding ->
        super.setContentView(binding.root)
    }

    private fun runAppNameAnimations() {
        this.runAppNameStartAnimation()
        this.runAppNameEndAnimation()
    }

    private fun runAppNameEndAnimation() =
        this.viewBinding?.let { binding: SplashscreenActivityBinding ->
            binding.appNameEnd.apply {
                this.alpha = 0f
                this.translationX = -TRANSLATION_X
                this.visibility = View.VISIBLE
                this.animate()
                    .alpha(1f)
                    .translationXBy(TRANSLATION_X)
                    .setDuration(this@SplashscreenActivity.animationDuration)
                    .start()
            }
        }

    private fun runAppNameStartAnimation() =
        this.viewBinding?.let { binding: SplashscreenActivityBinding ->
            binding.appNameStart.apply {
                this.alpha = 0f
                this.translationX = TRANSLATION_X
                this.visibility = View.VISIBLE
                this.animate()
                    .alpha(1f)
                    .setDuration(this@SplashscreenActivity.animationDuration)
                    .translationXBy(-TRANSLATION_X)
                    .start()
            }
        }

    private fun runLogoAnimations() {
        this.runLogoSpinningAnimation()
        this.runLogoFadeAnimation()
    }

    private fun runLogoFadeAnimation() =
        this.viewBinding?.let { binding: SplashscreenActivityBinding ->
            binding.logo.animate()
                .setStartDelay(LOGO_FADE_ANIMATION_DELAY)
                .withStartAction {
                    binding.logo.apply {
                        this.setImageResource(R.drawable.kotlin_logo_black)
                        this.setColorFilter(
                            SharedColorUtils.white(this@SplashscreenActivity)
                        )
                    }
                }
                .start()
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
            this.viewBinding?.let { binding: SplashscreenActivityBinding ->
                ObjectAnimator.ofPropertyValuesHolder(binding.logo, propertyValuesHolder)
            }
        logoAnimator?.apply {
            this.duration = 3600
            this.interpolator = LinearOutSlowInInterpolator()
            this.addListener(onEnd = { this@SplashscreenActivity.runAppNameAnimations() })
        }?.start()
    }
}