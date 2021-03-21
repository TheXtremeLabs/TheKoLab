package fr.lvmvrquxl.thekolab.colors.repository

import fr.lvmvrquxl.thekolab.colors.model.Colors
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of the colors repository.
 */
internal class ColorsRepositoryImpl private constructor(
    private val activityReference: ActivityReference
) : ColorsRepository {
    companion object {
        private val instance: ColorsRepository? by lazy {
            this.activityReference?.let { reference: ActivityReference ->
                ColorsRepositoryImpl(reference)
            }
        }

        private var activityReference: ActivityReference? = null

        /**
         * Get the repository's instance.
         * If no instance is available, this method creates a new instance of the repository.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return Instance of the repository
         */
        fun instance(activityReference: ActivityReference): ColorsRepository? {
            this.activityReference = activityReference
            return this.instance
        }
    }

    private val colors: Colors = Colors.create(this.activityReference)

    private var colorBackup: Color? = null

    override suspend fun backupColor(color: Color) {
        this.colorBackup = color
    }

    override suspend fun firstColor(): Color? = when (this.colorBackup) {
        null -> withContext(Dispatchers.Default) { this@ColorsRepositoryImpl.colors.default() }
        else -> this.colorBackup
    }

    override suspend fun randomColor(): Color = withContext(Dispatchers.Default) {
        this@ColorsRepositoryImpl.colors.random()
    }
}