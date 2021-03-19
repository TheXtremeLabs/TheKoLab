package fr.lvmvrquxl.thekolab.colors.repository

import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Interface of colors repository.
 *
 * This interface should be used for accessing colors data from model layer.
 */
internal interface ColorsRepository {
    companion object {
        /**
         * Get instance of colors repository.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return Instance of colors repository
         */
        fun instance(activityReference: ActivityReference): ColorsRepository? =
            ColorsRepositoryImpl.instance(activityReference)
    }

    /**
     * Backup given color, which will be the first displayed color when user reopens
     * the colors activity.
     *
     * @param color Color to backup
     */
    suspend fun backupColor(color: Color)

    /**
     * Get the first color to display.
     *
     * @return First color to display
     */
    suspend fun firstColor(): Color?

    /**
     * Get a random color to display.
     *
     * @return Random color
     */
    suspend fun randomColor(): Color?
}