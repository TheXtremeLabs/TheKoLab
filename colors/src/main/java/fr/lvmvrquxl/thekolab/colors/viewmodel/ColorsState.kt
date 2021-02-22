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

    /**
     * The user tries to exit the activity.
     *
     * @since 1.0.0
     */
    EXIT,

    /**
     * The activity starts.
     *
     * @since 1.0.0
     */
    START,

    /**
     * The user tries to update the colors.
     *
     * @since 1.0.0
     */
    UPDATE
}