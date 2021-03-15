package fr.lvmvrquxl.thekolab.shared.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.lvmvrquxl.thekolab.shared.view.LifecycleObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * Manager of the activity's states.
 *
 * @since 2.0.0
 */
abstract class StateManager : LifecycleObserver {
    companion object {
        /**
         * Close state.
         *
         * @since 2.0.0
         */
        const val CLOSE: String = "close"

        /**
         * Resume state.
         *
         * @since 2.0.0
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
     *
     * @since 2.0.0
     */
    val currentState: LiveData<String>
        get() = this.currentStateData

    private var currentStateData: MutableLiveData<String> = MutableLiveData()
    private var currentStateValue: String? = null

    @CallSuper
    override fun onCreate() = this.setCurrentState(CREATE)

    @CallSuper
    override fun onDestroy() = this.setCurrentState(DESTROY)

    @CallSuper
    override fun onPause() = this.setCurrentState(PAUSE)

    @CallSuper
    override fun onResume() = this.setCurrentState(RESUME)

    @CallSuper
    override fun onStart() = this.setCurrentState(START)

    @CallSuper
    override fun onStop() = this.setCurrentState(STOP)

    /**
     * Notify that the application is closing.
     *
     * @since 2.0.0
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
     *
     * @since 2.0.0
     */
    protected fun currentStateEquals(value: String): Boolean = runBlocking(Dispatchers.Default) {
        this@StateManager.currentStateValue == value
    }

    /**
     * Update current state of the activity.
     *
     * @param value New value
     *
     * @since 2.0.0
     */
    protected fun setCurrentState(value: String) {
        runBlocking(Dispatchers.Default) { this@StateManager.currentStateValue = value }
        this.syncCurrentState()
    }

    private fun syncCurrentState() =
        this.currentStateValue?.let { state: String -> this.currentStateData.value = state }
}