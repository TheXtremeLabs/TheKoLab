package fr.lvmvrquxl.thekolab.splashscreen.view.app_name

import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel

// TODO: Add documentation
internal abstract class AppNameView(
    private val activity: AppCompatActivity,
    view: MaterialTextView
) : AnimatedView(activity, view) {
    companion object {
        // TODO: Add documentation
        const val TRANSLATION_X: Float = 128f
    }

    protected val viewModel: SplashscreenViewModel = SplashscreenViewModel.instance()

    override fun observeViewModel() =
        this.viewModel.state.observe(this.activity) { state: SplashscreenState ->
            if (SplashscreenState.SHOW_APP_NAME == state) super.showStartAnimation()
        }

    @CallSuper
    override fun onResume() {
        super.hide()
        super.setVisible()
    }
}