package fr.lvmvrquxl.thekolab.colors.utils

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.R
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils

/**
 * String utilities useful for retrieving strings from resources.
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
     */
    fun blue(context: Context): String = context.getString(this.blue)

    /**
     * Get orange string from resources.
     *
     * @param context Context for retrieving string
     *
     * @return Orange string
     */
    fun orange(context: Context): String = SharedStringUtils.orange(context)

    /**
     * Get purple string from resources.
     *
     * @param context Context for retrieving string
     *
     * @return Purple string
     */
    fun purple(context: Context): String = SharedStringUtils.purple(context)

    /**
     * Get red string from resources.
     *
     * @param context Context for retrieving string
     *
     * @return Red string
     */
    fun red(context: Context): String = context.getString(this.red)

    /**
     * Get white string from resources.
     *
     * @param context Context for retrieving string
     *
     * @return White string
     */
    fun white(context: Context): String = SharedStringUtils.white(context)
}