package fr.lvmvrquxl.thekolab.home.toolbar.time.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.lvmvrquxl.thekolab.home.R
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarTimeFragmentBinding
import fr.lvmvrquxl.thekolab.shared.view.FragmentView

/**
 * Home toolbar's time fragment.
 *
 * This fragment is used for displaying date and time in the view pager of the home page activity.
 *
 * @since 1.0.0
 *
 * @see [Fragment]
 */
@Deprecated("Should be refactored for version 2.1.0")
internal class ToolbarTimeFragment private constructor() : Fragment(LAYOUT) {
    companion object {
        private val LAYOUT: Int = R.layout.toolbar_time_fragment

        /**
         * Create a new instance of toolbar's time fragment.
         *
         * @return New instance of toolbar's time fragment
         *
         * @since 1.0.0
         *
         * @see [Fragment]
         */
        fun create(): Fragment = ToolbarTimeFragment()
    }

    private var view: FragmentView<ToolbarTimeFragmentBinding>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.view = ToolbarTimeView.create(inflater, container)
        this.view?.onCreateView()
        return this.view?.root
    }

    override fun onDestroyView() {
        this.view?.onDestroyView()
        this.view = null
        super.onDestroyView()
    }

    override fun onPause() {
        this.view?.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        this.view?.onResume()
    }

    override fun onStart() {
        super.onStart()
        this.view?.onStart()
    }
}