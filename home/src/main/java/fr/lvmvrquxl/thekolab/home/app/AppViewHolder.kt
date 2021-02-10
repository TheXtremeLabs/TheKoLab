package fr.lvmvrquxl.thekolab.home.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.lvmvrquxl.thekolab.home.R
import fr.lvmvrquxl.thekolab.home.app.model.App

internal class AppViewHolder private constructor(
    inflater: LayoutInflater,
    private val parent: ViewGroup,
    attachToRoot: Boolean = false
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.app_item, parent, attachToRoot)) {
    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup): AppViewHolder =
            AppViewHolder(inflater, parent)
    }

    private val description: TextView = super.itemView.findViewById(R.id.app_item_description)
    private val name: TextView = super.itemView.findViewById(R.id.app_item_name)
    private val root: View = super.itemView.rootView

    fun bind(app: App) {
        this.description.text = app.description
        this.name.text = app.name
        this.root.setOnClickListener { app.start(this.parent.context) }
    }
}