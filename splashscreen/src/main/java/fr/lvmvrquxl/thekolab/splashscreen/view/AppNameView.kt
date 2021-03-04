package fr.lvmvrquxl.thekolab.splashscreen.view

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

    override fun observeState() =
        this.viewModel.state.observe(this.activity) { state: SplashscreenState ->
            when (state) {
                SplashscreenState.SHOW_APP_NAME -> super.showStartAnimation()
                SplashscreenState.STOP -> this.onStop()
                else -> {
                }
            }
        }

    @CallSuper
    override fun onStart() {
        super.hide()
        super.setVisible()
    }
}