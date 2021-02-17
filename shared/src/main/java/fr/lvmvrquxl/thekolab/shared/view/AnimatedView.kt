package fr.lvmvrquxl.thekolab.shared.view

interface AnimatedView : LifecycleView {
    fun showStartAnimation()

    fun showExitAnimation()

    fun showUpdateAnimation(): Unit?
}