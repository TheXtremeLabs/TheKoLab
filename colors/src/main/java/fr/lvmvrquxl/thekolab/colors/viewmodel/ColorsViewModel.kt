package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.repository.IColorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

internal object ColorsViewModel : ViewModel(), IColorsViewModel {
    override val actionStatus: LiveData<ColorsActionStatus>
        get() = this.actionStatusData
    override val color: LiveData<Color>
        get() = this.colorData

    private val actionStatusData: MutableLiveData<ColorsActionStatus> = MutableLiveData()
    private val colorData: MutableLiveData<Color> = MutableLiveData()
    private var context: Context? = null
    private var currentColor: Color? = null
    private var previousColor: Color? = null
    private var repository: IColorsRepository? = null

    override fun exit() {
        this.actionStatusData.value = ColorsActionStatus.EXIT
    }

    override fun onDestroy() {
        this.backupColor()
        this.context = null
        this.currentColor = null
        this.previousColor = null
        this.repository = null
    }

    override fun onStart() {
        this.initCurrentColor()
        this.syncColor()
        this.actionStatusData.value = ColorsActionStatus.START
    }

    override fun previousColor(): Color? = this.previousColor

    override fun updateColor() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.syncColor()
        this.actionStatusData.value = ColorsActionStatus.UPDATE
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
        if (this@ColorsViewModel.context != context) this@ColorsViewModel.context = context
    }

    private fun initCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentColor = this@ColorsViewModel.repository?.firstColor
    }

    private fun initRepository() = runBlocking(Dispatchers.Default) {
        if (null == this@ColorsViewModel.repository)
            this@ColorsViewModel.repository =
                this@ColorsViewModel.context?.let { context: Context ->
                    IColorsRepository.withContext(context)
                }
    }

    private fun pickRandomColor(): Color? = runBlocking(Dispatchers.Default) {
        var color: Color? = this@ColorsViewModel.repository?.randomColor
        while (this@ColorsViewModel.currentColor == color)
            color = this@ColorsViewModel.repository?.randomColor
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