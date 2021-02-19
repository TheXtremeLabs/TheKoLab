package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color

/**
 * The view model's interface of the colors activity.
 *
 * @since 1.0.0
 */
internal interface IColorsViewModel {
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
        fun instance(context: Context): IColorsViewModel = ColorsViewModel.withContext(context)
    }

    /**
     * Current color to be displayed.
     *
     * @since 1.0.0
     *
     * @see Color
     * @see LiveData
     */
    val color: LiveData<Color>

    /**
     * Previous color that was displayed.
     *
     * @since 1.0.0
     *
     * @see Color
     */
    val previousColor: Color?

    /**
     * Current state of the colors activity.
     *
     * @since 1.0.0
     *
     * @see ColorsState
     * @see LiveData
     */
    val state: LiveData<ColorsState>

    /**
     * Destroy the colors activity.
     *
     * @since 1.0.0
     */
    fun destroyActivity()

    /**
     * Callback when the user click on the back button of the navigation bar.
     *
     * @since 1.0.0
     */
    fun onBackPressed()

    /**
     * Callback when the view is destroying.
     *
     * @since 1.0.0
     */
    fun onDestroy()

    /**
     * Callback when the view is starting.
     *
     * @since 1.0.0
     */
    fun onStart()

    /**
     * Update the color to be displayed.
     *
     * @since 1.0.0
     */
    fun updateColor()
}