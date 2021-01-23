package fr.lvmvrquxl.thekolab.base

import android.view.View
import androidx.viewbinding.ViewBinding

abstract class BaseView<VB : ViewBinding> {
    protected var viewBinding: VB? = null

    open fun onDestroy() {
        this.viewBinding = null
    }

    open fun onDestroyView() = this.onDestroy()

    fun root(): View? = this.viewBinding?.root
}