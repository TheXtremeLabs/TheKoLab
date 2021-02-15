package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

internal class ColorsViewModel private constructor(private val context: Context) : ViewModel() {
    companion object {
        fun create(context: Context): ColorsViewModel = ColorsViewModel(context)
    }

    private val color: MutableLiveData<Color> = MutableLiveData()
    private val colors: List<Color> = listOf(
        Color(StringUtils.white(this.context)) { context: Context -> ColorUtils.white(context) },
        Color(StringUtils.orange(this.context)) { context: Context -> ColorUtils.orange(context) },
        Color(StringUtils.purple(this.context)) { context: Context -> ColorUtils.purple(context) },
        Color(StringUtils.blue(this.context)) { context: Context -> ColorUtils.blue(context) },
        Color(StringUtils.red(this.context)) { context: Context -> ColorUtils.red(context) }
    )
    private var currentColor: Color = this.colors.first()
    private var previousColor: Color? = null

    fun color(): LiveData<Color> = this.color

    fun onStart() = this.syncColor()

    fun previousColor(): Color? = this.previousColor

    fun updateColor() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.syncColor()
    }

    private fun pickRandomColor(): Color {
        var color: Color = this.colors.random()
        while (this.currentColor == color) color = this.colors.random()
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