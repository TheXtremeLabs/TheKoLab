package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.Color
import kotlinx.coroutines.Job

internal interface IColorsRepository {
    companion object {
        fun withContext(context: Context): IColorsRepository = ColorsRepository.withContext(context)
    }

    val firstColor: Color?
    val randomColor: Color?

    fun backupColor(color: Color): Job
}