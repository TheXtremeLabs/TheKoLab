package fr.lvmvrquxl.thekolab.shared.utils.animation

/**
 * Properties enumeration that can be animated by [ArgbAnimation].
 *
 * @param value Value corresponding to the property
 *
 * @since 1.0.0
 *
 * @see ArgbAnimation
 */
enum class ArgbAnimationProperty(val value: String) {
    /**
     * Background color's property (`backgroundColor`) of a view.
     *
     * @since 1.0.0
     */
    BACKGROUND_COLOR("backgroundColor"),

    /**
     * Color filter's property (`colorFilter`) of a view.
     *
     * @since 1.0.0
     */
    COLOR_FILTER("colorFilter"),

    /**
     * Text color's property (`textColor`) of a view.
     *
     * @since 1.0.0
     */
    TEXT_COLOR("textColor")
}