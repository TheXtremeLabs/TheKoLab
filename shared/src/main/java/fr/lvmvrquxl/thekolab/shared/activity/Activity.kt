package fr.lvmvrquxl.thekolab.shared.activity

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.shared.view.LifecycleObserver

/**
 * Base class of all activities of the application.
 *
 * @since 2.0.0
 */
abstract class Activity : AppCompatActivity() {
    /**
     * Add an observer responsible for tracking lifecycle events of the activity.
     * For removing an observer, use the [removeObserver] method instead.
     *
     * @param observer New observer
     *
     * @since 2.0.0
     */
    fun addObserver(observer: LifecycleObserver) = this.lifecycle.addObserver(observer)

    /**
     * Remove an observer responsible for tracking lifecycle events of the activity.
     * For adding an observer, use the [addObserver] method instead.
     *
     * @param observer Observer to remove
     *
     * @since 2.0.0
     */
    fun removeObserver(observer: LifecycleObserver) = this.lifecycle.removeObserver(observer)
}