package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.Color

internal interface IColorsRepository {
    companion object {
        fun withContext(context: Context): IColorsRepository = ColorsRepository.withContext(context)
    }

    val firstColor: Color?
    val randomColor: Color?

    fun backupColor(color: Color)
}