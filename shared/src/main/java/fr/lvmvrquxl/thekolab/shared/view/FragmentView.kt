package fr.lvmvrquxl.thekolab.shared.view

import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding

/**
 * Parent of all fragments view.
 *
 * @since 1.0.0
 *
 * @see [ActivityView]
 * @see [FragmentView]
 * @see [ViewBinding]
 */
abstract class FragmentView<VB : ViewBinding> : BindableView<VB>() {
    /**
     * Callback when the view is destroying.
     *
     * @since 1.0.0
     */
    @CallSuper
    open fun onDestroyView() {
        super.onDestroy()
    }

    /**
     * Callback when the view is saving instance state.
     *
     * @since 1.0.0
     */
    open fun onSaveInstanceState() {}

    /**
     * Callback when the view is created.
     *
     * @since 1.0.0
     */
    open fun onViewCreated() {}

    /**
     * Callback when the view state is restored.
     *
     * @since 1.0.0
     */
    open fun onViewStateRestored() {}

    /**
     * Callback when the view is creating.
     *
     * @since 1.0.0
     */
    fun onCreateView() {
        this.bindView()
        super.onCreate()
    }
}