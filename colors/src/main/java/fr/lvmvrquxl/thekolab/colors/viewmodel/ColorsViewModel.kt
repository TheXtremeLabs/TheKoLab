package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.repository.IColorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

internal object ColorsViewModel : ViewModel(), IColorsViewModel {
    override val actionState: LiveData<ColorsActionState>
        get() = this.actionStateData
    override val color: LiveData<Color>
        get() = this.colorData
    override val previousColor: Color?
        get() = this.previousColorData

    private val actionStateData: MutableLiveData<ColorsActionState> = MutableLiveData()
    private val colorData: MutableLiveData<Color> = MutableLiveData()
    private var context: Context? = null
    private var currentActionState: ColorsActionState? = null
    private var currentColor: Color? = null
    private var previousColorData: Color? = null
    private var repository: IColorsRepository? = null

    override fun destroyActivity() {
        this.setCurrentActionStateToClosable()
        this.syncActionState()
    }

    override fun onBackPressed() {
        if (ColorsActionState.EXIT != this.currentActionState) {
            this.setCurrentActionStateToExit()
            this.syncActionState()
        }
    }

    override fun onDestroy() {
        this.backupColor()
        this.context = null
        this.currentActionState = null
        this.currentColor = null
        this.previousColorData = null
        this.repository = null
    }

    override fun onStart() {
        this.initCurrentColor()
        this.setCurrentActionStateToStart()
        this.syncColor()
        this.syncActionState()
    }

    override fun updateColor() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.setCurrentActionStateToUpdate()
        this.syncColor()
        this.syncActionState()
    }

    fun withContext(context: Context): IColorsViewModel = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.initContext(context)
        this@ColorsViewModel.initRepository()
        this@ColorsViewModel
    }

    private fun backupColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentColor?.let { color: Color ->
            this@ColorsViewModel.repository?.backupColor(color)
        }
    }

    private fun initContext(context: Context) = runBlocking(Dispatchers.Default) {
        if (null == this@ColorsViewModel.context) this@ColorsViewModel.context = context
    }

    private fun initCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentColor = this@ColorsViewModel.repository?.firstColor
    }

    private fun initRepository() = runBlocking(Dispatchers.Default) {
        if (null == this@ColorsViewModel.repository)
            this@ColorsViewModel.repository =
                this@ColorsViewModel.context?.let { context: Context ->
                    IColorsRepository.instance(context)
                }
    }

    private fun pickRandomColor(): Color? = runBlocking(Dispatchers.Default) {
        var color: Color? = this@ColorsViewModel.repository?.randomColor
        while (this@ColorsViewModel.currentColor == color)
            color = this@ColorsViewModel.repository?.randomColor
        color
    }

    private fun setCurrentActionStateToClosable() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentActionState = ColorsActionState.CLOSABLE
    }

    private fun setCurrentActionStateToExit() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentActionState = ColorsActionState.EXIT
    }

    private fun setCurrentActionStateToStart() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentActionState = ColorsActionState.START
    }

    private fun setCurrentActionStateToUpdate() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentActionState = ColorsActionState.UPDATE
    }

    private fun syncActionState() {
        this.actionStateData.value = this.currentActionState
    }

    private fun syncColor() {
        this.colorData.value = this.currentColor
    }

    private fun updateCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentColor = this@ColorsViewModel.pickRandomColor()
    }

    private fun updatePreviousColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.previousColorData = this@ColorsViewModel.currentColor
    }
}