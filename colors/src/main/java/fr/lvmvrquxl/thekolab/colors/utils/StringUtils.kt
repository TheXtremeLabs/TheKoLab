package fr.lvmvrquxl.thekolab.colors.utils

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.R
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils

/**
 * String utilities.
 *
 * This singleton object should be used for retrieving strings from resources.
 *
 * @since 1.0.0
 */
internal object StringUtils {
    private val blue: Int = R.string.blue
    private val red: Int = R.string.red

    /**
     * Get blue string from resources.
     *
     * @param context Context for retrieving string
     *
     * @return Blue string
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun blue(context: Context): String = context.getString(this.blue)

    /**
     * Get orange string from resources.
     *
     * @param context Context for retrieving string
     *
     * @return Orange string
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun orange(context: Context): String = SharedStringUtils.orange(context)

    /**
     * Get purple string from resources.
     *
     * @param context Context for retrieving string
     *
     * @return Purple string
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun purple(context: Context): String = SharedStringUtils.purple(context)

    /**
     * Get red string from resources.
     *
     * @param context Context for retrieving string
     *
     * @return Red string
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun red(context: Context): String = context.getString(this.red)

    /**
     * Get white string from resources.
     *
     * @param context Context for retrieving string
     *
     * @return White string
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun white(context: Context): String = SharedStringUtils.white(context)
}