package fr.lvmvrquxl.thekolab.home.toolbar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Home toolbar's adapter.
 *
 * This class is responsible of managing the fragments display in the toolbar's view pager.
 *
 * @since 2.0.0
 */
internal class ToolbarAdapter private constructor(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    companion object {
        /**
         * Create an instance of home toolbar's adapter.
         *
         * @param activityReference Reference of the activity that contains the view pager
         * @param fragments Fragments to display in the view pager
         *
         * @return New instance of home toolbar's adapter
         *
         * @since 2.0.0
         */
        fun create(
            activityReference: ActivityReference,
            fragments: List<Fragment>
        ): FragmentStateAdapter? = activityReference.get()?.let { activity: Activity ->
            ToolbarAdapter(activity.supportFragmentManager, activity.lifecycle, fragments)
        }
    }

    override fun createFragment(position: Int): Fragment = this.fragments[position]

    override fun getItemCount(): Int = this.fragments.size
}