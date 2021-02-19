package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color

internal interface IColorsViewModel {
    companion object {
        fun instance(context: Context): IColorsViewModel = ColorsViewModel.withContext(context)
    }

    val actionState: LiveData<ColorsActionState>
    val color: LiveData<Color>

    fun close()

    fun exit()

    fun onDestroy()

    fun onStart()

    fun previousColor(): Color?

    fun updateColor()
}