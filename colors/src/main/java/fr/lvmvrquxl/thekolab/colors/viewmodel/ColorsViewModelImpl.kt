package fr.lvmvrquxl.thekolab.colors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.repository.IColorsRepository
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * The view model of the colors activity.
 *
 * @since 1.0.0
 *
 * @see ColorsViewModel
 * @see ViewModel
 */
internal object ColorsViewModelImpl : ColorsViewModel() {
    override val color: LiveData<Color>
        get() = this.colorData

    override val previousColor: Color?
        get() = this.previousColorData

    override val state: LiveData<ColorsState>
        get() = this.stateData

    private val colorData: MutableLiveData<Color> = MutableLiveData()
    private val stateData: MutableLiveData<ColorsState> = MutableLiveData()

    private var currentState: ColorsState? = null
    private var currentColor: Color? = null
    private var previousColorData: Color? = null
    private var repository: IColorsRepository? = null

    override fun closeActivity() {
        this.setCurrentStateToClosable()
        this.syncState()
    }

    override fun onBackPressed() {
        if (ColorsState.EXIT != this.currentState) {
            this.setCurrentStateToExit()
            this.syncState()
        }
    }

    override fun onCleared() {
        this.currentState = null
        this.currentColor = null
        this.previousColorData = null
        this.repository = null
    }

    override fun onCreate() {
        this.currentState = ColorsState.CREATE
        super.activity?.let { activity: Activity ->
            this.repository = IColorsRepository.instance(activity)
        }
        this.syncState()
    }

    override fun onDestroy() {
        this.currentState = ColorsState.DESTROY
        this.syncState()
        super.onDestroy()
    }

    override fun onPause() {
        this.currentState = ColorsState.PAUSE
        this.syncState()
    }

    override fun onResume() {
        this.currentState = ColorsState.RESUME
        this.syncState()
    }

    override fun onStart() {
        this.setCurrentStateToStart()
        this.currentColor = this.repository?.firstColor
        this.syncColor()
        this.syncState()
    }

    override fun onStop() {
        this.currentState = ColorsState.STOP
        this.backupColor()
        this.syncState()
    }

    override fun updateColor() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.setCurrentStateToUpdate()
        this.syncColor()
        this.syncState()
    }

    private fun backupColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.currentColor?.let { color: Color ->
            this@ColorsViewModelImpl.repository?.backupColor(color)
        }
    }

    private fun pickRandomColor(): Color? = runBlocking(Dispatchers.Default) {
        var color: Color? = this@ColorsViewModelImpl.repository?.randomColor
        while (this@ColorsViewModelImpl.currentColor == color)
            color = this@ColorsViewModelImpl.repository?.randomColor
        color
    }

    private fun setCurrentStateToClosable() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.currentState = ColorsState.CLOSABLE
    }

    private fun setCurrentStateToExit() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.currentState = ColorsState.EXIT
    }

    private fun setCurrentStateToStart() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.currentState = ColorsState.START
    }

    private fun setCurrentStateToUpdate() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.currentState = ColorsState.UPDATE
    }

    private fun syncColor() =
        this.currentColor?.let { color: Color -> this.colorData.value = color }

    private fun syncState() =
        this.currentState?.let { state: ColorsState -> this.stateData.value = state }

    private fun updateCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.currentColor = this@ColorsViewModelImpl.pickRandomColor()
    }

    private fun updatePreviousColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModelImpl.previousColorData = this@ColorsViewModelImpl.currentColor
    }
}