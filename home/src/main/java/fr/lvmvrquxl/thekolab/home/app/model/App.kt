package fr.lvmvrquxl.thekolab.home.app.model

import android.content.Context
import android.content.Intent
import fr.lvmvrquxl.thekolab.colors.activity.ColorsActivity

/**
 * Model representation of an application.
 *
 * @param activity Activity to start when the user clicks on the application
 * @param description Description of the application
 * @param name Name of the application
 *
 * @since 1.0.0
 *
 * @see ColorsActivity
 */
internal class App(val name: String, val description: String, val activity: Class<ColorsActivity>) {
    override fun equals(other: Any?): Boolean = other is App && this.activity == other.activity

    override fun hashCode(): Int =
        this.name.hashCode() + this.description.hashCode() + this.activity.hashCode()

    /**
     * Start the application's activity.
     *
     * @param context Context for starting new activity
     *
     * @since 1.0.0
     *
     * @see Context
     */
    fun start(context: Context) = context.startActivity(Intent(context, this.activity))
}