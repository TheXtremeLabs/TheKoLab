package fr.lvmvrquxl.thekolab.base

import android.view.View
import androidx.viewbinding.ViewBinding

abstract class BaseView<VB : ViewBinding> {
    val root: View?
        get(): View? = this.viewBinding?.root
    protected var viewBinding: VB? = null

    open fun onDestroy() {
        this.viewBinding = null
    }

    open fun onDestroyView() = this.onDestroy()

    open fun onPause(): Unit? = null

    open fun onResume() {}
}