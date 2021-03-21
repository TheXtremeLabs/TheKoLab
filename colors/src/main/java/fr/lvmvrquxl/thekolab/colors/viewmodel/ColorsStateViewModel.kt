package fr.lvmvrquxl.thekolab.colors.viewmodel

import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.viewmodel.StateViewModel

/**
 * View model of the colors activity's state.
 */
internal class ColorsStateViewModel private constructor(activityReference: ActivityReference) :
    StateViewModel(activityReference) {
    companion object {
        /**
         * Change colors state.
         */
        const val CHANGE_COLORS: String = "change colors"

        /**
         * Exit state.
         */
        const val EXIT: String = "exit"

        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the activity to observe
         *
         * @return New instance of the view model
         */
        fun observe(activityReference: ActivityReference): ColorsStateViewModel =
            ColorsStateViewModel(activityReference)
    }

    /**
     * Notify that the activity should change displayed colors.
     */
    fun changeColors() = super.setCurrentState(CHANGE_COLORS)

    /**
     * Notify that the user wants to exit the activity.
     */
    fun exit() = super.setCurrentState(EXIT)

    /**
     * Check if the activity is exiting.
     *
     * @return
     *  - `true`: the activity's current state equals [EXIT]
     *  - `false`: the activity's current state is not equal to [EXIT]
     */
    fun isExiting(): Boolean = super.currentStateEquals(EXIT)
}