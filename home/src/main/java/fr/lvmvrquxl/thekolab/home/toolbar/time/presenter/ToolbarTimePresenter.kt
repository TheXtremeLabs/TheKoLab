package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter

/**
 * Presenter's interface of the toolbar's time display.
 *
 * This presenter is responsible for managing the background tasks of the toolbar's time display.
 *
 * @since 0.1.3
 */
internal interface ToolbarTimePresenter {
    companion object {
        /**
         * Build a new toolbar's time presenter.
         *
         * This method creates a new presenter for managing the toolbar's time display.
         *
         * @param callback Callback with time updater
         * @return The new toolbar's time presenter
         *
         * @since 0.1.3
         * @see [ToolbarTimeCallback]
         */
        fun create(callback: ToolbarTimeCallback): ToolbarTimePresenter =
            ToolbarTimePresenterImpl(callback)
    }

    /**
     * Cancel all coroutines in the presenter's scope.
     *
     * This method cancels all running coroutines in the presenter's scope.
     *
     * @since 0.1.3
     */
    fun cancelCoroutines()

    /**
     * Launch background coroutines.
     *
     * @since 0.1.3
     */
    fun startBackgroundCoroutines()
}