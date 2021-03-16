package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.viewmodel.ViewModel

/**
 * The view model's interface of the colors activity.
 *
 * @since 1.0.0
 */
internal abstract class ColorsViewModel : ViewModel() {
    companion object {
        /**
         * Instance of the view model.
         *
         * @since 2.0.0
         */
        val instance: ColorsViewModel by lazy { ColorsViewModelImpl.create() }
    }

    /**
     * Current color to be displayed.
     *
     * @since 1.0.0
     */
    abstract val color: LiveData<Color>?

    /**
     * Previous color that was displayed.
     *
     * @since 1.0.0
     */
    abstract val previousColor: Color?

    /**
     * Current state of the activity.
     *
     * @since 2.0.0
     */
    abstract val state: LiveData<String>?

    /**
     * Change colors to be displayed.
     *
     * @since 2.0.0
     */
    abstract fun changeColors()

    /**
     * Observe the given activity's lifecycle.
     *
     * @param activity Instance of the colors activity
     *
     * @since 2.0.0
     */
    abstract fun observe(activity: Activity)
}