package fr.lvmvrquxl.thekolab.shared.view

/**
 * Parent of all animated views.
 *
 * @since 1.0.0
 *
 * @see LifecycleView
 */
abstract class AnimatedView : LifecycleView() {
    /**
     * Show exit animation.
     *
     * @since 1.0.0
     */
    abstract fun showExitAnimation()

    /**
     * Show start animation.
     *
     * @since 1.0.0
     */
    abstract fun showStartAnimation()

    /**
     * Show update animation.
     *
     * @since 1.0.0
     */
    abstract fun showUpdateAnimation()
}