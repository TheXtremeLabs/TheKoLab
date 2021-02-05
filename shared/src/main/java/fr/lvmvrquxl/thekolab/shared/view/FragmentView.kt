package fr.lvmvrquxl.thekolab.shared.view

import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding

abstract class FragmentView<VB : ViewBinding> : ActivityView<VB>() {
    open fun onCreateView() {}

    /**
     * Destroy the view.
     *
     * Destroy the current view binding setting it to `null`.
     * For reading purpose, this method should be called from views manipulating elements of a
     * fragment.
     * If your view is manipulating elements of an activity, consider using [onDestroy] instead.
     *
     * @since 0.1.3
     * @see onDestroy
     */
    @CallSuper
    open fun onDestroyView() = this.onDestroy()

    open fun onSaveInstanceState() {}

    open fun onViewCreated() {}

    open fun onViewStateRestored() {}
}