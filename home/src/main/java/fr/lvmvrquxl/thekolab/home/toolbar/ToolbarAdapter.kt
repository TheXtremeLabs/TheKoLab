package fr.lvmvrquxl.thekolab.home.toolbar

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Home toolbar's adapter.
 *
 * This class is responsible of managing the fragments display in the toolbar's view pager.
 *
 * @param fragmentManager Manager for interacting with fragments
 * @param lifecycle Lifecycle of the view pager's host
 * @param fragments List of fragments to display in the view pager
 *
 * @since 1.0.0
 *
 * @see [Fragment]
 * @see [FragmentManager]
 * @see [FragmentStateAdapter]
 * @see [Lifecycle]
 * @see [List]
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
         * @param activity Activity containing the view pager
         * @param fragments Fragments to display in the view pager
         *
         * @return New instance of home toolbar's adapter
         *
         * @since 1.0.0
         *
         * @see [AppCompatActivity]
         * @see [Fragment]
         * @see [FragmentStateAdapter]
         * @see [List]
         */
        fun create(activity: AppCompatActivity, fragments: List<Fragment>): FragmentStateAdapter =
            ToolbarAdapter(activity.supportFragmentManager, activity.lifecycle, fragments)
    }

    override fun createFragment(position: Int): Fragment = this.fragments[position]

    override fun getItemCount(): Int = this.fragments.size
}