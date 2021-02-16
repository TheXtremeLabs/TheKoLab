package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.repository.IColorsRepository

internal class ColorsViewModel private constructor(private val context: Context) : ViewModel(),
    IColorsViewModel {
    companion object {
        fun create(context: Context): IColorsViewModel = ColorsViewModel(context)
    }

    override val color: LiveData<Color>
        get() = this.colorData

    private val colorData: MutableLiveData<Color> = MutableLiveData()
    private val repository: IColorsRepository = IColorsRepository.withContext(this.context)
    private var currentColor: Color = this.repository.firstColor
    private var previousColor: Color? = null

    override fun onDestroy() {
        this.repository.backupColor(this.currentColor)
        this.previousColor = null
    }

    override fun onStart() = this.syncColor()

    override fun previousColor(): Color? = this.previousColor

    override fun updateColor() {
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
        this.colorData.value = this.currentColor
    }

    private fun updateCurrentColor() {
        this.currentColor = this.pickRandomColor()
    }

    private fun updatePreviousColor() {
        this.previousColor = this.currentColor
    }
}