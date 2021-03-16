package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import kotlinx.coroutines.Job

/**
 * Interface of colors repository.
 *
 * This interface should be used for accessing colors data from model layer.
 *
 * @since 1.0.0
 */
internal interface ColorsRepository {
    companion object {
        /**
         * Get instance of colors repository.
         *
         * @param context Context of the repository
         *
         * @return Instance of colors repository
         *
         * @since 1.0.0
         *
         * @see [Context]
         */
        fun instance(context: Context): ColorsRepository = ColorsRepositoryImpl.withContext(context)
    }

    /**
     * First color to display.
     *
     * @since 1.0.0
     *
     * @see [Color]
     */
    val firstColor: Color?

    /**
     * Random color to display.
     *
     * @since 1.0.0
     *
     * @see [Color]
     */
    val randomColor: Color?

    /**
     * Backup given color, which will be the first displayed color.
     *
     * @param color Color to backup
     *
     * @return Coroutine's job responsible for saving color
     *
     * @since 1.0.0
     *
     * @see [Color]
     * @see [Job]
     */
    fun backupColor(color: Color): Job
}