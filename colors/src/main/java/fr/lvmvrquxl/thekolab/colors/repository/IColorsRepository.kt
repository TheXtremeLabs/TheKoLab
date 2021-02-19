package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import kotlinx.coroutines.Job

internal interface IColorsRepository {
    companion object {
        fun instance(context: Context): IColorsRepository = ColorsRepository.withContext(context)
    }

    val firstColor: Color?
    val randomColor: Color?

    fun backupColor(color: Color): Job
}