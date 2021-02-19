package fr.lvmvrquxl.thekolab.shared.view

abstract class AnimatedView : LifecycleView() {
    abstract fun showExitAnimation()

    abstract fun showStartAnimation()

    abstract fun showUpdateAnimation()
}