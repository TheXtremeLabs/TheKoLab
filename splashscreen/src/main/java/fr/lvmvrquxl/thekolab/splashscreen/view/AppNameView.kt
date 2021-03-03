package fr.lvmvrquxl.thekolab.splashscreen.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel

// TODO: Add documentation
internal abstract class AppNameView(
    private val activity: AppCompatActivity,
    view: MaterialTextView
) : SplashscreenAnimatedView(activity, view) {
    companion object {
        // TODO: Add documentation
        const val TRANSLATION_X: Float = 128f
    }

    private val viewModel: SplashscreenViewModel = SplashscreenViewModel.instance()

    override fun observeState() =
        this.viewModel.state.observe(this.activity) { state: SplashscreenState ->
            if (SplashscreenState.SHOW_APP_NAME == state) super.showStartAnimation()
        }
}