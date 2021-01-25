package fr.lvmvrquxl.thekolab.home.presenter

import fr.lvmvrquxl.thekolab.home.view.HomeToolbarTimeView

/**
 * Presenter's interface of the toolbar's time display.
 *
 * This presenter is responsible for managing the background tasks of the toolbar's time display.
 *
 * @since 0.1.3
 */
interface HomeToolbarTimePresenter {
    companion object {
        /**
         * Build a new toolbar's time presenter.
         *
         * This method creates a new presenter for managing the toolbar's time display.
         *
         * @param view View of the toolbar's time display
         * @return The new toolbar's time presenter
         *
         * @since 0.1.3
         * @see [HomeToolbarTimeView]
         */
        fun build(view: HomeToolbarTimeView): HomeToolbarTimePresenter =
            HomeToolbarTimePresenterImpl(view)
    }

    /**
     * Cancel all coroutines in the presenter's scope.
     *
     * This method cancels all running coroutines in the presenter's scope.
     *
     * @since 0.1.3
     */
    fun cancelCoroutineScope()

    /**
     * Cancel time updater's job.
     *
     * This method cancels only the job responsible for updating the time.
     *
     * @since 0.1.3
     */
    fun cancelTimeUpdaterJob(): Unit?

    /**
     * Get current date.
     *
     * This method gets the current date of the device.
     *
     * @return The current date of the device
     *
     * @since 0.1.3
     */
    fun getCurrentDate(): String

    /**
     * Launch time updater's job.
     *
     * This method launches the job responsible for updating the time.
     *
     * @since 0.1.3
     */
    fun launchTimeUpdater()
}