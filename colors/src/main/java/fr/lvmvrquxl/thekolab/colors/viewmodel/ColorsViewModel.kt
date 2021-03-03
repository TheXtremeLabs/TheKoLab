package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.viewmodel.ViewModel

/**
 * The view model's interface of the colors activity.
 *
 * @since 1.0.0
 */
internal abstract class ColorsViewModel : ViewModel() {
    companion object {
        /**
         * Get instance of the view model.
         *
         * @param context Context of the view model
         *
         * @return Instance of the view model
         *
         * @since 1.0.0
         *
         * @see Context
         */
        fun instance(context: Context): ColorsViewModel = ColorsViewModelImpl.withContext(context)
    }

    /**
     * Current color to be displayed.
     *
     * @since 1.0.0
     *
     * @see Color
     * @see LiveData
     */
    abstract val color: LiveData<Color>

    /**
     * Previous color that was displayed.
     *
     * @since 1.0.0
     *
     * @see Color
     */
    abstract val previousColor: Color?

    /**
     * Current state of the colors activity.
     *
     * @since 1.0.0
     *
     * @see ColorsState
     * @see LiveData
     */
    abstract val state: LiveData<ColorsState>

    /**
     * Update the color to be displayed.
     *
     * @since 1.0.0
     */
    abstract fun updateColor()
}