package fr.lvmvrquxl.thekolab.home.toolbar

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Home toolbar's adapter.
 *
 * This class is mainly used for managing the fragments displaying in the toolbar's view pager.
 * For design purpose, it should not be invoked directly by the activity but by its
 * corresponding view (ex : [HomeView] use this class, instead of [HomeActivity]).
 *
 * @param fragmentManager Manager for interacting with fragments associated with the [HomeActivity]
 * @param lifecycle Lifecycle of the [HomeActivity]
 * @param fragments List of fragments to display in the view pager
 *
 * @since 0.1.3
 * @see [HomeActivity]
 * @see [HomeView]
 */
internal class HomeToolbarAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    companion object {
        fun create(activity: AppCompatActivity, fragments: List<Fragment>): HomeToolbarAdapter =
            HomeToolbarAdapter(activity.supportFragmentManager, activity.lifecycle, fragments)
    }

    override fun createFragment(position: Int): Fragment = this.fragments[position]

    override fun getItemCount(): Int = this.fragments.size
}