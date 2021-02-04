package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter

internal interface ToolbarTimeCallback {
    fun updateDate(date: String)

    /**
     * Update toolbar's time text.
     *
     * This method updates the time displayed in the toolbar.
     *
     * @param time New time to display
     *
     * @since 0.1.3
     */
    fun updateTime(time: String)
}