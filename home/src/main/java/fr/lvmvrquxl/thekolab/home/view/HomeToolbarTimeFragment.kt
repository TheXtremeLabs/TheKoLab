package fr.lvmvrquxl.thekolab.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.lvmvrquxl.thekolab.home.R
import fr.lvmvrquxl.thekolab.home.databinding.HomeToolbarTimeFragmentBinding
import fr.lvmvrquxl.thekolab.shared.view.BaseView

/**
 * Home toolbar's time fragment.
 *
 * This fragment is used for displaying date and time in the view pager of the home page activity.
 *
 * @since 0.1.3
 * @see [HomeActivity]
 */
class HomeToolbarTimeFragment : Fragment(LAYOUT) {
    companion object {
        private val LAYOUT: Int = R.layout.home_toolbar_time_fragment
    }

    private var view: BaseView<HomeToolbarTimeFragmentBinding>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.view = HomeToolbarTimeView(inflater, container)
        return this.view?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.view?.onDestroyView()
        this.view = null
    }

    override fun onPause() {
        super.onPause()
        this.view?.onPause()
    }

    override fun onResume() {
        super.onResume()
        this.view?.onResume()
    }
}