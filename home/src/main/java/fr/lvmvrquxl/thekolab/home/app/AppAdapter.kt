package fr.lvmvrquxl.thekolab.home.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.lvmvrquxl.thekolab.home.app.model.App

/**
 * Application's adapter of the recycler view.
 *
 * @param apps List of applications
 *
 * @since 1.0.0
 *
 * @see AppViewHolder
 * @see RecyclerView.Adapter
 */
internal class AppAdapter(private val apps: List<App>) : RecyclerView.Adapter<AppViewHolder>() {
    override fun getItemCount(): Int = this.apps.size

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app: App = this.apps[position]
        holder.bind(app)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AppViewHolder.create(inflater, parent)
    }
}