package fr.lvmvrquxl.thekolab.colors.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.repository.IColorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * The view model of the colors activity.
 *
 * @since 1.0.0
 *
 * @see IColorsViewModel
 * @see ViewModel
 */
internal object ColorsViewModel : ViewModel(), IColorsViewModel {
    override val color: LiveData<Color>
        get() = this.colorData
    override val previousColor: Color?
        get() = this.previousColorData
    override val state: LiveData<ColorsState>
        get() = this.stateData

    private val colorData: MutableLiveData<Color> = MutableLiveData()
    private val stateData: MutableLiveData<ColorsState> = MutableLiveData()
    private var context: Context? = null
    private var currentState: ColorsState? = null
    private var currentColor: Color? = null
    private var previousColorData: Color? = null
    private var repository: IColorsRepository? = null

    override fun destroyActivity() {
        this.setCurrentStateToClosable()
        this.syncState()
    }

    override fun onBackPressed() {
        if (ColorsState.EXIT != this.currentState) {
            this.setCurrentStateToExit()
            this.syncState()
        }
    }

    override fun onDestroy() {
        this.backupColor()
        this.context = null
        this.currentState = null
        this.currentColor = null
        this.previousColorData = null
        this.repository = null
    }

    override fun onStart() {
        this.initCurrentColor()
        this.setCurrentStateToStart()
        this.syncColor()
        this.syncState()
    }

    override fun updateColor() {
        this.updatePreviousColor()
        this.updateCurrentColor()
        this.setCurrentStateToUpdate()
        this.syncColor()
        this.syncState()
    }

    /**
     * Set the current context and init a new repository with it.
     *
     * @param context New context
     *
     * @return Instance of the view model
     *
     * @since 1.0.0
     *
     * @see Context
     * @see IColorsViewModel
     */
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

    private fun setCurrentStateToClosable() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentState = ColorsState.CLOSABLE
    }

    private fun setCurrentStateToExit() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentState = ColorsState.EXIT
    }

    private fun setCurrentStateToStart() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentState = ColorsState.START
    }

    private fun setCurrentStateToUpdate() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentState = ColorsState.UPDATE
    }

    private fun syncColor() {
        this.colorData.value = this.currentColor
    }

    private fun syncState() {
        this.stateData.value = this.currentState
    }

    private fun updateCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.currentColor = this@ColorsViewModel.pickRandomColor()
    }

    private fun updatePreviousColor() = runBlocking(Dispatchers.Default) {
        this@ColorsViewModel.previousColorData = this@ColorsViewModel.currentColor
    }
}