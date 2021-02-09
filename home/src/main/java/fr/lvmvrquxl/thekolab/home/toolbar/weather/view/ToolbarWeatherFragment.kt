package fr.lvmvrquxl.thekolab.home.toolbar.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import fr.lvmvrquxl.thekolab.home.R
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarWeatherFragmentBinding
import fr.lvmvrquxl.thekolab.shared.view.FragmentView

/**
 * Toolbar's weather fragment.
 *
 * @since 0.1.3
 *
 * @see [Fragment]
 */
internal class ToolbarWeatherFragment private constructor() : Fragment(LAYOUT) {
    companion object {
        private val LAYOUT: Int = R.layout.toolbar_weather_fragment

        /**
         * Create a new instance of the toolbar's weather.
         *
         * @return New instance of the toolbar's weather
         *
         * @since 0.1.3
         *
         * @see [Fragment]
         */
        fun create(): Fragment = ToolbarWeatherFragment()
    }

    private var view: FragmentView<ToolbarWeatherFragmentBinding>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.view = this.activity?.let { activity: FragmentActivity ->
            ToolbarWeatherView.create(inflater, container, activity)
        }
        this.view?.onCreateView()
        return this.view?.root
    }

    override fun onDestroyView() {
        this.view?.onDestroyView()
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
        this.view?.onStart()
        super.onStart()
    }
}