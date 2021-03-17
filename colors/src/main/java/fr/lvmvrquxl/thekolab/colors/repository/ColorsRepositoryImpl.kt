package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.model.IColors
import kotlinx.coroutines.*

/**
 * Implementation of the colors repository.
 *
 * @since 1.0.0
 */
internal object ColorsRepositoryImpl : ColorsRepository {
    private var colorBackup: Color? = null
    private var colors: IColors? = null

    override suspend fun backupColor(color: Color) {
        this.colorBackup = color
    }

    override suspend fun firstColor(): Color? = when (this.colorBackup) {
        null -> this.colors?.default
        else -> this.colorBackup
    }

    override suspend fun randomColor(): Color? = this.colors?.random

    /**
     * Update context and init new colors to display.
     *
     * @param context New context
     *
     * @return Instance of repository
     *
     * @since 1.0.0
     */
    fun withContext(context: Context): ColorsRepository = runBlocking(Dispatchers.Default) {
        this@ColorsRepositoryImpl.colors = IColors.create(context)
        this@ColorsRepositoryImpl
    }
}