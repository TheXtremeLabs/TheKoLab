package fr.lvmvrquxl.thekolab.colors.utils

import android.content.Context
import androidx.core.content.ContextCompat
import fr.lvmvrquxl.thekolab.colors.R
import fr.lvmvrquxl.thekolab.shared.utils.SharedColorUtils

/**
 * Color utilities.
 *
 * This singleton object should be used for retrieving colors from resources.
 *
 * @since 1.0.0
 */
internal object ColorUtils {
    private val blue: Int = R.color.blue
    private val red: Int = R.color.red

    /**
     * Get blue color from resources.
     *
     * @param context Context for retrieving color
     *
     * @return Blue color's value
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun blue(context: Context): Int = ContextCompat.getColor(context, this.blue)

    /**
     * Get orange color from resources.
     *
     * @param context Context for retrieving color
     *
     * @return Orange color's value
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun orange(context: Context): Int = SharedColorUtils.orange(context)

    /**
     * Get purple color from resources.
     *
     * @param context Context for retrieving color
     *
     * @return Purple color's value
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun purple(context: Context): Int = SharedColorUtils.purple(context)

    /**
     * Get red color from resources.
     *
     * @param context Context for retrieving color
     *
     * @return Red color's value
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun red(context: Context): Int = ContextCompat.getColor(context, this.red)

    /**
     * Get white color from resources.
     *
     * @param context Context for retrieving color
     *
     * @return White color's value
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun white(context: Context): Int = SharedColorUtils.white(context)
}