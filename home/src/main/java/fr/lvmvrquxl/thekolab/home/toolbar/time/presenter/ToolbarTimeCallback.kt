package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter

/**
 * Callback of the toolbar's time view.
 *
 * @since 1.0.0
 */
internal interface ToolbarTimeCallback {
    /**
     * Update toolbar's date text.
     *
     * @param date New date text
     *
     * @since 1.0.0
     */
    fun updateDate(date: String)

    /**
     * Update toolbar's time text.
     *
     * @param time New time to display
     *
     * @since 1.0.0
     */
    fun updateTime(time: String)
}