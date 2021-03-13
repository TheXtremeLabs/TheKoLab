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
        val instance: ColorsViewModel = ColorsViewModelImpl
    }

    /**
     * Current color to be displayed.
     *
     * @since 1.0.0
     */
    abstract val color: LiveData<Color>

    /**
     * Previous color that was displayed.
     *
     * @since 1.0.0
     */
    abstract val previousColor: Color?

    /**
     * Current state of the colors activity.
     *
     * @since 1.0.0
     */
    abstract val state: LiveData<ColorsState>

    /**
     * Instance of the colors activity.
     *
     * @since 2.0.0
     */
    protected var activity: Activity? = null

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    /**
     * Update the color to be displayed.
     *
     * @since 1.0.0
     */
    abstract fun updateColor()

    /**
     * Observe the given activity's lifecycle.
     *
     * @param activity Splashscreen's activity
     *
     * @since 2.0.0
     */
    fun observe(activity: Activity) =
        activity.apply { this@ColorsViewModel.activity = this }.addObserver(this)

    private fun stopActivityObservation() = this.activity?.removeObserver(this)
}