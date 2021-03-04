package fr.lvmvrquxl.thekolab.colors.viewmodel

/**
 * States enumeration of the colors activity.
 *
 * @since 1.0.0
 */
internal enum class ColorsState {
    /**
     * The activity can be closed.
     *
     * @since 1.0.0
     */
    CLOSABLE,

    // TODO: Add documentation
    CREATE,

    // TODO: Add documentation
    DESTROY,

    /**
     * The user tries to exit the activity.
     *
     * @since 1.0.0
     */
    EXIT,

    // TODO: Add documentation
    PAUSE,

    // TODO: Add documentation
    RESUME,

    /**
     * The activity starts.
     *
     * @since 1.0.0
     */
    START,

    // TODO: Add documentation
    STOP,

    /**
     * The user tries to update the colors.
     *
     * @since 1.0.0
     */
    UPDATE
}