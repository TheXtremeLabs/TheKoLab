package fr.lvmvrquxl.thekolab.colors.repository

import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Interface of colors repository.
 *
 * This interface should be used for accessing colors data from model layer.
 *
 * @since 2.0.0
 */
internal interface ColorsRepository {
    companion object {
        /**
         * Get instance of colors repository.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return Instance of colors repository
         *
         * @since 2.0.0
         */
        fun instance(activityReference: ActivityReference): ColorsRepository =
            ColorsRepositoryImpl.apply {
                activityReference.get()?.let { activity: Activity -> this.withContext(activity) }
            }
    }

    /**
     * Backup given color, which will be the first displayed color when user reopens
     * the colors activity.
     *
     * @param color Color to backup
     *
     * @since 2.0.0
     */
    suspend fun backupColor(color: Color)

    /**
     * Get the first color to display.
     *
     * @return First color to display
     *
     * @since 2.0.0
     */
    suspend fun firstColor(): Color?

    /**
     * Get a random color to display.
     *
     * @return Random color
     *
     * @since 2.0.0
     */
    suspend fun randomColor(): Color?
}