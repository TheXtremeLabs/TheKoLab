package fr.lvmvrquxl.thekolab.shared.view

import android.view.View
import androidx.viewbinding.ViewBinding

// TODO: Add documentation
abstract class BindableView<VB : ViewBinding> : ContainerView() {
    /**
     * Root of the view.
     *
     * @since 1.0.0
     *
     * @see [View]
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

    // TODO: Add documentation
    protected abstract fun bindView()

    private fun destroyViewBinding() {
        this.viewBinding = null
    }
}