package fr.lvmvrquxl.thekolab.shared.view

import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * Parent of application's bindable views.
 *
 * Bindable views should be activities or fragments.
 *
 * @param VB View binding's type
 *
 * @since 2.0.0
 */
abstract class BindableView<VB : ViewBinding> : ContainerView() {
    /**
     * Root of the view.
     *
     * @since 1.0.0
     */
    val root: View?
        get(): View? = this.viewBinding?.root

    /**
     * View binding.
     *
     * @since 1.0.0
     */
    protected var viewBinding: VB? = null

    override fun onDestroy() {
        this.destroyViewBinding()
        super.onDestroy()
    }

    /**
     * Bind the typed view.
     *
     * @since 2.0.0
     */
    protected abstract fun bindView()

    private fun destroyViewBinding() {
        this.viewBinding = null
    }
}