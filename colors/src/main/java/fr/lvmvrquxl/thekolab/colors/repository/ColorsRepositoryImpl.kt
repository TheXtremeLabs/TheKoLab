package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.model.Colors
import kotlinx.coroutines.*

/**
 * Implementation of the colors repository.
 */
internal object ColorsRepositoryImpl : ColorsRepository {
    private var colorBackup: Color? = null
    private var colors: Colors? = null

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
     */
    fun withContext(context: Context): ColorsRepository = runBlocking(Dispatchers.Default) {
        this@ColorsRepositoryImpl.colors = Colors.create(context)
        this@ColorsRepositoryImpl
    }
}