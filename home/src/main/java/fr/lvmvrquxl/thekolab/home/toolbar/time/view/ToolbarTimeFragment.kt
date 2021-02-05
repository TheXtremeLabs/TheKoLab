package fr.lvmvrquxl.thekolab.home.toolbar.time.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.lvmvrquxl.thekolab.home.R
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarTimeFragmentBinding
import fr.lvmvrquxl.thekolab.shared.view.BaseView

/**
 * Home toolbar's time fragment.
 *
 * This fragment is used for displaying date and time in the view pager of the home page activity.
 *
 * @since 0.1.3
 */
internal class ToolbarTimeFragment : Fragment(LAYOUT) {
    companion object {
        private val LAYOUT: Int = R.layout.toolbar_time_fragment

        fun create(): ToolbarTimeFragment = ToolbarTimeFragment()
    }

    private var view: BaseView<ToolbarTimeFragmentBinding>? = null

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