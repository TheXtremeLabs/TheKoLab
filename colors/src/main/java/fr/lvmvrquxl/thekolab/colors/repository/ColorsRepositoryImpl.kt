package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.model.IColors
import kotlinx.coroutines.*

/**
 * Implementation of the colors repository.
 *
 * @since 1.0.0
 *
 * @see [ColorsRepository]
 */
internal object ColorsRepositoryImpl : ColorsRepository {
    override val firstColor: Color?
        get() = when (this.colorBackup) {
            null -> this.colors?.default
            else -> this.colorBackup
        }
    override val randomColor: Color?
        get() = this.colors?.random

    private const val SCOPE_NAME: String = "ColorsRepository"
    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    private var colorBackup: Color? = null
    private var colors: IColors? = null

    override fun backupColor(color: Color): Job = this.coroutineScope.launch {
        this@ColorsRepositoryImpl.colorBackup = color
    }

    /**
     * Update context and init new colors to display.
     *
     * @param context New context
     *
     * @return Instance of repository
     *
     * @since 1.0.0
     *
     * @see [Context]
     * @see [ColorsRepository]
     */
    fun withContext(context: Context): ColorsRepository = runBlocking(Dispatchers.Default) {
        this@ColorsRepositoryImpl.colors = IColors.create(context)
        this@ColorsRepositoryImpl
    }
}