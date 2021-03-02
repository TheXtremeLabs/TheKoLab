package fr.lvmvrquxl.thekolab.splashscreen.view

import android.animation.ArgbEvaluator
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addListener
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.utils.SharedColorUtils
import fr.lvmvrquxl.thekolab.splashscreen.R
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding

class SplashscreenActivity : AppCompatActivity() {
    companion object {
        private const val TRANSLATION_X: Float = 128f
        private val layout: Int = R.layout.splashscreen_activity
        private val tag: String = SplashscreenActivity::class.java.simpleName
    }

    private val animationDuration: Long
        get() = this.resources.getInteger(android.R.integer.config_longAnimTime).toLong()

    private var viewBinding: SplashscreenActivityBinding? = null

    // Views
    private var constraintLayout: ConstraintLayout? = null
    private var tvThe: MaterialTextView? = null
    private var tvOLab: MaterialTextView? = null
    private var llLogoBackgroundCover: LinearLayout? = null
    private var ivLogo: ShapeableImageView? = null

    // Animators
    private var logoColorAnimator: ObjectAnimator? = null
    private var backgroundColorFadeAnimator: ObjectAnimator? = null

    private val lLogoFadeColorDelay: Long = 1500


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bindView()
        super.setContentView(layout)
//        initViews()
//        initTextViewsVisibility()
    }

    override fun onStart() {
        super.onStart()
//        this.startLogoRotationAnimation()
    }

    private fun bindView() {
        this.viewBinding = SplashscreenActivityBinding.inflate(this.layoutInflater)
    }

    /**
     * Initialize views in the layout
     */
    private fun initViews() {
        constraintLayout = findViewById(R.id.constraint_layout)
        tvThe = findViewById(R.id.app_name_start)
        tvOLab = findViewById(R.id.app_name_end)
        llLogoBackgroundCover = findViewById(R.id.logo_background)
        ivLogo = findViewById(R.id.logo)
    }


    /**
     * Set text views visibility to gone, in order to hide them at the launch of the app
     */
    private fun initTextViewsVisibility() {
        tvThe!!.visibility = View.GONE
        tvOLab!!.visibility = View.GONE
    }

    /**
     * Run logo animation. Animation is set on y axis by default
     */
    private fun startLogoRotationAnimation() {

        // Set Key Frames values to set logo position at a specific time
        val kf0: Keyframe = Keyframe.ofFloat(0f, 0f)
        val kf1: Keyframe = Keyframe.ofFloat(.2f, 360f * 1.5f)
        val kf2: Keyframe = Keyframe.ofFloat(.35f, 360f * 3.0f)
        val kf3: Keyframe = Keyframe.ofFloat(.4f, 360f * 5.0f)
        val kf4: Keyframe = Keyframe.ofFloat(.5f, 360f * 7.0f)
        val kf5: Keyframe = Keyframe.ofFloat(.75f, 360f * 9.0f)
        val kf6: Keyframe = Keyframe.ofFloat(1f, 360f * 10f)

        val propertyName = "rotationY"

        // Set property view holder
        val propertyValuesHolder: PropertyValuesHolder =
            PropertyValuesHolder
                .ofKeyframe(
                    propertyName,
                    kf0, kf1, kf2, kf3, kf4, kf5, kf6
                )

        // apply property view holder object to the object animator
        logoColorAnimator = ObjectAnimator.ofPropertyValuesHolder(ivLogo, propertyValuesHolder)

        // set object animator properties
        logoColorAnimator!!.duration = 3500
        logoColorAnimator!!.interpolator = LinearOutSlowInInterpolator()
        logoColorAnimator!!.addListener(
            onEnd = {
                Log.e(tag, "End logo animation")

                llLogoBackgroundCover!!.visibility = View.VISIBLE

                // Display textviews
                tvThe!!.visibility = View.VISIBLE
                tvThe?.alpha = 0f
                tvOLab!!.visibility = View.VISIBLE
                tvOLab?.alpha = 0f

                // Call slides animations
                slideToLeftAnimation()
                slideToRightAnimation()
            })
        logoColorAnimator!!.start()

        // Delay Logo color fade animation
        Handler(Looper.getMainLooper())
            .postDelayed(
                {
                    Log.e(tag, "Run Logo color fade animation")
                    fadeBackgroundView(ivLogo as View, 0, 0)
                },
                lLogoFadeColorDelay
            )
    }

    private fun fadeBackgroundView(view: View, baseColor: Int, targetColor: Int) {
        if (view is ShapeableImageView) {
            ivLogo!!.setImageResource(R.drawable.kotlin_logo_black)
            ivLogo!!.setColorFilter(
                SharedColorUtils.white(this),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }

        if (view is ConstraintLayout) {

            backgroundColorFadeAnimator =
                ObjectAnimator.ofObject(
                    view,
                    "backgroundColor",
                    ArgbEvaluator(),
                    baseColor,  // Beginning color
                    targetColor// Target Color
                )

            backgroundColorFadeAnimator!!.duration = 1000
            backgroundColorFadeAnimator!!.start()
        }
    }

    /**
     * /!\ Important to know /!\ : https://stackoverflow.com/questions/29175429/objectanimator-vs-translateanimation/29187285
     *
     *
     * The difference is mainly that if you use a TranslateAnimation,
     * the view which you are animating does not really leave its original position on the screen,
     * it just makes it look like it is moving.
     * So the view basically doesnt change its coordinates.
     *
     *
     * If you use an ObjectAnimator the view really changes its actual position.
     */
    private fun slideToLeftAnimation() {
        Log.d(tag, "slideToLeftAnimation()")
        tvThe?.translationX = TRANSLATION_X
        tvThe!!.animate()
            .alpha(1f)
            .translationXBy(-TRANSLATION_X)
            .setDuration(this.animationDuration)
    }

    private fun slideToRightAnimation() {
        Log.d(tag, "slideToRightAnimation()")
        tvOLab?.translationX = -TRANSLATION_X
        tvOLab!!.animate()
            .alpha(1f)
            .translationXBy(TRANSLATION_X)
            .setDuration(this.animationDuration)
    }
}