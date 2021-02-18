package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.model.IColors
import kotlinx.coroutines.*

internal object ColorsRepository : IColorsRepository {
    override val firstColor: Color?
        get() = when (this.colorBackup) {
            null -> this.colors?.first
            else -> this.colorBackup
        }
    override val randomColor: Color?
        get() = this.colors?.random

    private const val SCOPE_NAME: String = "ColorsRepository"
    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    private var colorBackup: Color? = null
    private var colors: IColors? = null

    override fun backupColor(color: Color): Job = this.coroutineScope.launch {
        this@ColorsRepository.colorBackup = color
    }

    fun withContext(context: Context): IColorsRepository = runBlocking(Dispatchers.Default) {
        this@ColorsRepository.initColors(context)
        this@ColorsRepository
    }

    private fun initColors(context: Context) = this.coroutineScope.launch {
        this@ColorsRepository.colors = IColors.create(context)
    }
}