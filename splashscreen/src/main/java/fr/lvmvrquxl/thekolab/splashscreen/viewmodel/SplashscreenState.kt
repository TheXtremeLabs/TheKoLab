package fr.lvmvrquxl.thekolab.splashscreen.viewmodel

// TODO: Add documentation
internal enum class SplashscreenState {
    /**
     * The activity can be closed.
     *
     * @since 1.0.0
     */
    CLOSABLE,

    /**
     * The user tries to exit the activity.
     *
     * @since 1.0.0
     */
    EXIT,

    // TODO: Add documentation
    SHOW_APP_NAME,

    // TODO: Add documentation
    SHOW_LOGO,

    /**
     * The activity starts.
     *
     * @since 1.0.0
     */
    START
}