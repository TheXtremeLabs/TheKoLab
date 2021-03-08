package fr.lvmvrquxl.thekolab.shared.view

import androidx.annotation.CallSuper

/**
 * Parent of all views that contains other views.
 *
 * @since 1.0.0
 */
abstract class ContainerView : LifecycleView() {
    @Deprecated("Will be removed in version 2.0.0")
    private var views: MutableList<LifecycleView>? = null

    @CallSuper
    override fun onCreate() = this.createViews()

    @CallSuper
    override fun onDestroy() = this.destroyViews()

    @CallSuper
    override fun onPause() {
        this.pauseViews()
    }

    @CallSuper
    override fun onResume() {
        this.resumeViews()
    }

    @CallSuper
    override fun onStart() {
        this.startViews()
    }

    @CallSuper
    override fun onStop() {
        this.stopViews()
    }

    protected open fun registerViews() {
    }

    /**
     * Add given view in this container.
     *
     * @param view View to add
     *
     * @since 1.0.0
     */
    @Deprecated("Will be removed in version 2.0.0")
    protected fun addView(view: LifecycleView) =
        this.views?.let { views: MutableList<LifecycleView> ->
            if (!views.contains(view)) views.add(view)
        }

    @Deprecated("Will be removed in version 2.0.0")
    private fun createViews() {
        this.views = mutableListOf()
        this.registerViews()
        this.views?.forEach { view: LifecycleView -> view.onCreate() }
    }

    @Deprecated("Will be removed in version 2.0.0")
    private fun destroyViews() {
        this.views?.apply {
            this.forEach { view: LifecycleView -> view.onDestroy() }
            this.clear()
        }
        this.views = null
    }

    @Deprecated("Will be removed in version 2.0.0")
    private fun pauseViews() = this.views?.forEach { view: LifecycleView -> view.onPause() }

    @Deprecated("Will be removed in version 2.0.0")
    private fun resumeViews() = this.views?.forEach { view: LifecycleView -> view.onResume() }

    @Deprecated("Will be removed in version 2.0.0")
    private fun startViews() = this.views?.forEach { view: LifecycleView -> view.onStart() }

    @Deprecated("Will be removed in version 2.0.0")
    private fun stopViews() = this.views?.forEach { view: LifecycleView -> view.onStop() }
}