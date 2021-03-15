package fr.lvmvrquxl.thekolab.colors.viewmodel

import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.viewmodel.StateManager

/**
 * Manager of the colors activity's state.
 *
 * @since 2.0.0
 */
internal class ColorsStateManager private constructor(private val activity: Activity) :
    StateManager() {
    companion object {
        /**
         * Change colors state.
         *
         * @since 2.0.0
         */
        const val CHANGE_COLORS: String = "change colors"

        /**
         * Exit state.
         *
         * @since 2.0.0
         */
        const val EXIT: String = "exit"

        /**
         * Observe the given activity's lifecycle.
         *
         * @return New instance of the manager
         *
         * @since 2.0.0
         */
        fun observe(activity: Activity) =
            ColorsStateManager(activity).apply { activity.addObserver(this) }
    }

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    /**
     * Notify that the activity should change displayed colors.
     *
     * @since 2.0.0
     */
    fun changeColors() = super.setCurrentState(CHANGE_COLORS)

    /**
     * Notify that the user wants to exit the activity.
     *
     * @since 2.0.0
     */
    fun exit() = super.setCurrentState(EXIT)

    /**
     * Check if the activity is exiting.
     *
     * @return
     *  - `true`: the activity's current state equals [EXIT]
     *  - `false`: the activity's current state is not equal to [EXIT]
     *
     * @since 2.0.0
     */
    fun isExiting(): Boolean = super.currentStateEquals(EXIT)

    private fun stopActivityObservation() = this.activity.removeObserver(this)
}