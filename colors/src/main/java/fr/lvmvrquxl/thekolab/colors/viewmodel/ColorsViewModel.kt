package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.repository.IColorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

internal class ColorsViewModel private constructor(private val context: Context) : ViewModel(),
    IColorsViewModel {
    companion object {
        fun create(context: Context): IColorsViewModel = ColorsViewModel(context)
    }

    override val color: LiveData<Color>
        get() = this.colorData

    private val colorData: MutableLiveData<Color> = MutableLiveData()
    private val repository: IColorsRepository = IColorsRepository.withContext(this.context)
    private var currentColor: Color? = null
    private var previousColor: Color? = null

    override fun onDestroy() {
        this.backupColor()
        this.currentColor = null
        this.previousColor = null
    }

    override fun onStart() {
        this.initCurrentColor()
        this.syncColor()
    }

    override fun previousColor(): Color? = this.previousColor

    override fun updateColor() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.syncColor()
    }

    private fun backupColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentColor?.let { color: Color ->
            this@ColorsViewModel.repository.backupColor(color)
        }
    }

    private fun initCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentColor = this@ColorsViewModel.repository.firstColor
    }

    private fun pickRandomColor(): Color? = runBlocking(Dispatchers.Default) {
        var color: Color? = this@ColorsViewModel.repository.randomColor
        while (this@ColorsViewModel.currentColor == color)
            color = this@ColorsViewModel.repository.randomColor
        color
    }

    private fun syncColor() {
        this.colorData.value = this.currentColor
    }

    private fun updateCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentColor = this@ColorsViewModel.pickRandomColor()
    }

    private fun updatePreviousColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.previousColor = this@ColorsViewModel.currentColor
    }
}