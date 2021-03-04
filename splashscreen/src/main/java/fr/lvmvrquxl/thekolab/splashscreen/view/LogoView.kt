package fr.lvmvrquxl.thekolab.splashscreen.view

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.google.android.material.imageview.ShapeableImageView
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel
import kotlinx.coroutines.Runnable

// TODO: Add documentation
internal class LogoView private constructor(
    private val activity: AppCompatActivity,
    private val view: ShapeableImageView
) : AnimatedView(activity, view) {
    companion object {
        private const val SPINNING_ANIMATION_DURATION: Long = 3200
        private const val START_ANIMATION_DELAY: Long = 250

        // TODO: Add documentation
        fun create(activity: AppCompatActivity, view: ShapeableImageView): AnimatedView =
            LogoView(activity, view)
    }

    override val startAnimation: Runnable
        get() = super.animation.apply {
            this.mediumDuration()
            this.delay(START_ANIMATION_DELAY)
            this.onStart { this@LogoView.spinningAnimation.run() }
        }

    private val spinningAnimation: Runnable
        get() = Runnable {
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
            ObjectAnimator.ofPropertyValuesHolder(this.view, propertyValuesHolder).apply {
                this.duration = SPINNING_ANIMATION_DURATION
                this.interpolator = LinearOutSlowInInterpolator()
                this.addListener(onEnd = { this@LogoView.viewModel.showAppName() })
            }.start()
        }

    private val viewModel: SplashscreenViewModel = SplashscreenViewModel.instance()

    init {
        super.hide()
    }

    override fun observeState() =
        this.viewModel.state.observe(this.activity) { state: SplashscreenState ->
            if (SplashscreenState.SHOW_LOGO == state) super.showStartAnimation()
        }
}