package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.viewmodel.ViewModel

/**
 * The view model's interface of the colors activity.
 */
internal abstract class ColorsViewModel : ViewModel() {
    companion object {
        /**
         * Instance of the view model.
         */
        val instance: ColorsViewModel by lazy { ColorsViewModelImpl.create() }
    }

    /**
     * Current color to be displayed.
     */
    abstract val color: LiveData<Color>?

    /**
     * Previous color that was displayed.
     */
    abstract val previousColor: Color?

    /**
     * Current state of the activity.
     */
    abstract val state: LiveData<String>?

    /**
     * Change colors to be displayed.
     */
    abstract fun changeColors()

    /**
     * Observe the given activity's lifecycle.
     *
     * @param activityReference Reference of the colors activity
     */
    abstract fun observe(activityReference: ActivityReference)
}