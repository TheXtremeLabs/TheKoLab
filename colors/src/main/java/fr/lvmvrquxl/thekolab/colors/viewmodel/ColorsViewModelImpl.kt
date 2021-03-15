package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.repository.IColorsRepository
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

// TODO: Change Singleton pattern to a lazy singleton with inner class builder

/**
 * The view model of the colors activity.
 *
 * @since 1.0.0
 */
internal object ColorsViewModelImpl : ColorsViewModel() {
    override val color: LiveData<Color>
        get() = this.colorData

    override val previousColor: Color?
        get() = this.previousColorData

    private val colorData: MutableLiveData<Color> = MutableLiveData()

    private var currentColor: Color? = null
    private var previousColorData: Color? = null
    private var repository: IColorsRepository? = null

    override fun onCleared() {
        this.currentColor = null
        this.previousColorData = null
        this.repository = null
        super.onCleared()
    }

    override fun onCreate() {
        super.activity?.let { activity: Activity ->
            this.repository = IColorsRepository.instance(activity)
        }
    }

    override fun onStart() {
        this.currentColor = this.repository?.firstColor
        this.syncColor()
    }

    override fun onStop() {
        this.backupColor()
    }

    override fun changeColors() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.syncColor()
        super.changeColors()
    }

    private fun backupColor() {
        runBlocking(Dispatchers.Default) {
            this@ColorsViewModelImpl.currentColor?.let { color: Color ->
                this@ColorsViewModelImpl.repository?.backupColor(color)
            }
        }
    }

    private fun pickRandomColor(): Color? = runBlocking(Dispatchers.Default) {
        var color: Color? = this@ColorsViewModelImpl.repository?.randomColor
        while (this@ColorsViewModelImpl.currentColor == color)
            color = this@ColorsViewModelImpl.repository?.randomColor
        color
    }

    private fun syncColor() =
        this.currentColor?.let { color: Color -> this.colorData.value = color }

    private fun updateCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.currentColor = this@ColorsViewModelImpl.pickRandomColor()
    }

    private fun updatePreviousColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.previousColorData = this@ColorsViewModelImpl.currentColor
    }
}