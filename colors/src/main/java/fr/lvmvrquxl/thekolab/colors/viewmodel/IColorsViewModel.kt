package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.colors.model.Color

internal interface IColorsViewModel {
    companion object {
        fun create(context: Context): IColorsViewModel = ColorsViewModel.create(context)
    }

    val color: LiveData<Color>

    fun onDestroy()

    fun onStart()

    fun previousColor(): Color?

    fun updateColor()
}