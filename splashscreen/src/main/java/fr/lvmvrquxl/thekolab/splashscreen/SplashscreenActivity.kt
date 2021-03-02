package fr.lvmvrquxl.thekolab.splashscreen

import android.animation.ArgbEvaluator
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Typeface
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

class SplashscreenActivity : AppCompatActivity() {
    companion object {
        private const val TRANSLATION_X: Float = 128f
        private val TAG = SplashscreenActivity::class.java.simpleName
    }

    // Context
    private var context: Context? = this

    // Views
    private var constraintLayout: ConstraintLayout? = null
    private var tvThe: MaterialTextView? = null
    private var tvOLab: MaterialTextView? = null
    private var llLogoBackgroundCover: LinearLayout? = null
    private var ivLogo: ShapeableImageView? = null

    // Animators
    private var logoColorAnimator: ObjectAnimator? = null
    private var backgroundColorFadeAnimator: ObjectAnimator? = null

    private var animationDuration: Int = 0
    private val lLogoFadeColorDelay: Long = 1500


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        animationDuration = resources.getInteger(android.R.integer.config_longAnimTime)

        initViews()

        initTextViewsFont()
        initTextViewsVisibility()
        this.startLogoRotationAnimation()
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
     * Set Textviews font type
     */
    private fun initTextViewsFont() {
        val typeface = Typeface.createFromAsset(context!!.assets, "LABRAT_.ttf")
        tvThe!!.typeface = typeface
        tvOLab!!.typeface = typeface
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
                Log.e(TAG, "End logo animation")

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
                    Log.e(TAG, "Run Logo color fade animation")
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
        Log.d(TAG, "slideToLeftAnimation()")
        tvThe?.translationX = TRANSLATION_X
        tvThe!!.animate()
            .alpha(1f)
            .translationXBy(-TRANSLATION_X)
            .setDuration(animationDuration.toLong())
            .setListener(null)
    }

    private fun slideToRightAnimation() {
        Log.d(TAG, "slideToRightAnimation()")
        tvOLab?.translationX = -TRANSLATION_X
        tvOLab!!.animate()
            .alpha(1f)
            .translationXBy(TRANSLATION_X)
            .setDuration(animationDuration.toLong())
            .setListener(null)
    }
}