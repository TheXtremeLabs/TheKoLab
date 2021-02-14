package fr.lvmvrquxl.thekolab.home.app.model

import android.content.Context
import android.content.Intent
import fr.lvmvrquxl.thekolab.colors.view.ColorsActivity

internal class App(val name: String, val description: String, val activity: Class<ColorsActivity>) {
    override fun equals(other: Any?): Boolean = other is App && this.activity == other.activity

    override fun hashCode(): Int =
        this.name.hashCode() + this.description.hashCode() + this.activity.hashCode()

    fun start(context: Context) = context.startActivity(Intent(context, this.activity))
}