package fr.lvmvrquxl.thekolab.shared.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * View model of the activity's states.
 *
 * @param activityReference Reference of the current activity
 */
abstract class StateViewModel(private val activityReference: ActivityReference) : ViewModel() {
    companion object {
        /**
         * Close state.
         */
        const val CLOSE: String = "close"

        /**
         * Resume state.
         */
        const val RESUME: String = "resume"
        private const val CREATE: String = "create"
        private const val DESTROY: String = "destroy"
        private const val PAUSE: String = "pause"
        private const val START: String = "start"
        private const val STOP: String = "stop"
    }

    /**
     * Current state of the application.
     */
    val currentState: LiveData<String>
        get() = this.currentStateData

    private val currentStateData: MutableLiveData<String> = MutableLiveData()

    private var currentStateValue: String? = null

    init {
        this.observeActivity()
    }

    override fun onCleared() = this.clearCurrentStateValue()

    @CallSuper
    override fun onCreate() = this.setCurrentState(CREATE)

    override fun onDestroy() {
        this.setCurrentState(DESTROY)
        this.stopActivityObservation()
        super.onDestroy()
    }

    @CallSuper
    override fun onPause() = this.setCurrentState(PAUSE)

    @CallSuper
    override fun onResume() = this.setCurrentState(RESUME)

    @CallSuper
    override fun onStart() = this.setCurrentState(START)

    @CallSuper
    override fun onStop() = this.setCurrentState(STOP)

    private fun clearCurrentStateValue() {
        this.currentStateValue = null
    }

    /**
     * Notify that the application is closing.
     */
    fun close() = this.setCurrentState(CLOSE)

    /**
     * Check if the current state equals the given value.
     *
     * @param value Value to check
     *
     * @return
     *  - `true` : the current state is equal to the given value
     *  - `false` : the current state is not equal to the given value
     */
    protected fun currentStateEquals(value: String): Boolean = this.currentStateValue == value

    /**
     * Update current state of the activity.
     *
     * @param value New value
     */
    protected fun setCurrentState(value: String) {
        this.currentStateValue = value
        this.syncCurrentState()
    }

    private fun observeActivity() {
        this.activityReference.get()?.addObserver(this)
    }

    private fun stopActivityObservation() {
        this.activityReference.get()?.removeObserver(this)
    }

    private fun syncCurrentState() {
        this.currentStateValue?.let { state: String -> this.currentStateData.value = state }
    }
}