package fr.lvmvrquxl.thekolab.shared.view

import androidx.annotation.CallSuper

abstract class ContainerView : LifecycleView() {
    protected val views: MutableList<LifecycleView> = mutableListOf()

    @CallSuper
    override fun onCreate() = this.createViews()

    @CallSuper
    override fun onDestroy() = this.destroyViews()

    @CallSuper
    override fun onStart() = this.startViews()

    private fun createViews() = this.views.forEach { view: LifecycleView -> view.onCreate() }

    private fun destroyViews() {
        this.views.forEach { view: LifecycleView -> view.onDestroy() }
        this.views.clear()
    }

    private fun startViews() = this.views.forEach { view: LifecycleView -> view.onStart() }
}