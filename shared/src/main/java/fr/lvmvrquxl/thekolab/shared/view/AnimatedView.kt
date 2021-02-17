package fr.lvmvrquxl.thekolab.shared.view

abstract class AnimatedView : LifecycleView {
    open fun showEntryAnimation() {}

    open fun showExitAnimation() {}

    open fun showUpdateAnimation(): Unit? = null
}