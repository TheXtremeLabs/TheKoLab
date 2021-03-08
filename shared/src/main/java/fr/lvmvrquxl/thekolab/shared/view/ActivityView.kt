package fr.lvmvrquxl.thekolab.shared.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Base class of all views.
 *
 * This abstract class is the parent of all views in the application.
 * According to the *Model-View-Presenter* design pattern, a view is **ONLY** responsible for
 * displaying data. The views of the application are following this pattern.
 *
 * @param VB View binding's type
 *
 * @since 1.0.0
 *
 * @see [ViewBinding]
 */
abstract class ActivityView<VB : ViewBinding>(private val activity: AppCompatActivity) :
    BindableView<VB>() {
    override fun onCreate() {
        this.bindView()
        super.onCreate()
        this.setContentView()
    }

    /**
     * Callback when the view is requesting permissions.
     *
     * @since 1.0.0
     */
    open fun onRequestPermissionsResult(grantResults: IntArray) {}

    private fun setContentView() =
        super.root?.let { view: View -> this.activity.setContentView(view) }
}