package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.repository.ColorsRepository

internal class ColorsViewModel private constructor(private val context: Context) : ViewModel() {
    companion object {
        fun create(context: Context): ColorsViewModel = ColorsViewModel(context)
    }

    private val color: MutableLiveData<Color> = MutableLiveData()
    private val repository: ColorsRepository = ColorsRepository.withContext(this.context)
    private var currentColor: Color = this.repository.firstColor
    private var previousColor: Color? = null

    fun color(): LiveData<Color> = this.color

    fun onDestroy() {
        this.repository.backupColor(this.currentColor)
        this.previousColor = null
    }

    fun onStart() = this.syncColor()

    fun previousColor(): Color? = this.previousColor

    fun updateColor() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.syncColor()
    }

    private fun pickRandomColor(): Color {
        var color: Color = this.repository.randomColor
        while (this.currentColor == color) color = this.repository.randomColor
        return color
    }

    private fun syncColor() {
        this.color.value = this.currentColor
    }

    private fun updateCurrentColor() {
        this.currentColor = this.pickRandomColor()
    }

    private fun updatePreviousColor() {
        this.previousColor = this.currentColor
    }
}