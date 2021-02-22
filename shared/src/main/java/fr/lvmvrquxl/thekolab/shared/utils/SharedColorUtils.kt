package fr.lvmvrquxl.thekolab.shared.utils

import android.content.Context
import androidx.core.content.ContextCompat
import fr.lvmvrquxl.thekolab.shared.R

/**
 * Utilities for managing shared colors.
 *
 * @since 1.0.0
 */
object SharedColorUtils {
    private val orange: Int = R.color.orange_300
    private val purple: Int = R.color.purple_300
    private val white: Int = R.color.white

    /**
     * Get orange color from resources.
     *
     * @param context Current context
     *
     * @return Orange color's value
     *
     * @since 1.0.0
     *
     * @see Context
     */
    fun orange(context: Context): Int = this.color(context, this.orange)

    /**
     * Get purple color from resources.
     *
     * @param context Current context
     *
     * @return Purple color's value
     *
     * @since 1.0.0
     *
     * @see Context
     */
    fun purple(context: Context): Int = this.color(context, this.purple)

    /**
     * Get white color from resources.
     *
     * @param context Current context
     *
     * @return White color's value
     *
     * @since 1.0.0
     *
     * @see Context
     */
    fun white(context: Context): Int = this.color(context, this.white)

    private fun color(context: Context, color: Int): Int = ContextCompat.getColor(context, color)
}