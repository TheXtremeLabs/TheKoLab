package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.model.Colors

internal object ColorsRepository : IColorsRepository {
    override val firstColor: Color?
        get() = when (null == this.colorBackup) {
            true -> this.colors?.first
            else -> this.colorBackup
        }
    override val randomColor: Color?
        get() = this.colors?.random

    private var colorBackup: Color? = null
    private var colors: Colors? = null

    override fun backupColor(color: Color) {
        this.colorBackup = color
    }

    fun withContext(context: Context): IColorsRepository {
        this.colors = Colors.create(context)
        return this
    }
}