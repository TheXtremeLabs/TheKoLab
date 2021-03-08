package fr.lvmvrquxl.thekolab.splashscreen.viewmodel

/**
 * Enumeration of the splashscreen activity's states.
 *
 * @since 2.0.0
 */
internal enum class SplashscreenState {
    /**
     * The activity is closing.
     *
     * @since 2.0.0
     */
    CLOSE,

    /**
     * The activity is creating.
     *
     * @since 2.0.0
     */
    CREATE,

    /**
     * The activity is destroying.
     *
     * @since 2.0.0
     */
    DESTROY,

    /**
     * The activity is pausing.
     *
     * @since 2.0.0
     */
    PAUSE,

    /**
     * The activity is resuming.
     *
     * @since 2.0.0
     */
    RESUME,

    /**
     * The activity is showing the application's name.
     *
     * @since 2.0.0
     */
    SHOW_APP_NAME,

    /**
     * The activity is showing the application's logo.
     *
     * @since 2.0.0
     */
    SHOW_LOGO,

    /**
     * The activity is showing the application's version name.
     *
     * @since 2.0.0
     */
    SHOW_VERSION_NAME,

    /**
     * The activity is starting.
     *
     * @since 2.0.0
     */
    START,

    /**
     * The activity is stopping.
     *
     * @since 2.0.0
     */
    STOP
}