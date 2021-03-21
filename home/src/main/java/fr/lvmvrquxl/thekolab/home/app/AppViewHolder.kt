package fr.lvmvrquxl.thekolab.home.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.lvmvrquxl.thekolab.home.R
import fr.lvmvrquxl.thekolab.home.app.model.App

/**
 * View holder of the applications listed in the recycler view.
 *
 * @param inflater Inflater of the layout
 * @param parent Parent of the view holder
 *
 * @since 1.0.0
 *
 * @see LayoutInflater
 * @see RecyclerView.ViewHolder
 * @see ViewGroup
 */
@Deprecated("Should be refactored for version 2.1.0")
internal class AppViewHolder private constructor(
    inflater: LayoutInflater,
    private val parent: ViewGroup
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.app_item, parent, false)) {
    companion object {
        /**
         * Create an instance of the view holder.
         *
         * @param inflater Inflater of the layout
         * @param parent Parent of the view holder
         *
         * @return New instance of the view holder
         *
         * @since 1.0.0
         *
         * @see LayoutInflater
         * @see ViewGroup
         */
        fun create(inflater: LayoutInflater, parent: ViewGroup): AppViewHolder =
            AppViewHolder(inflater, parent)
    }

    private val description: TextView = super.itemView.findViewById(R.id.app_item_description)
    private val name: TextView = super.itemView.findViewById(R.id.app_item_name)
    private val root: View = super.itemView.rootView

    /**
     * Bind given application.
     *
     * @param app Application to bind
     *
     * @since 1.0.0
     *
     * @see App
     */
    fun bind(app: App) {
        this.description.text = app.description
        this.name.text = app.name
        this.root.setOnClickListener { app.start(this.parent.context) }
    }
}