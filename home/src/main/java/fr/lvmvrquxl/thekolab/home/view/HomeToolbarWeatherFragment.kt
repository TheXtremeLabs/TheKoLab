package fr.lvmvrquxl.thekolab.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.lvmvrquxl.thekolab.home.R
import fr.lvmvrquxl.thekolab.home.databinding.HomeToolbarWeatherFragmentBinding
import fr.lvmvrquxl.thekolab.shared.view.BaseView

class HomeToolbarWeatherFragment : Fragment(LAYOUT) {
    companion object {
        private val LAYOUT: Int = R.layout.home_toolbar_weather_fragment
    }

    private var view: BaseView<HomeToolbarWeatherFragmentBinding>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.view = HomeToolbarWeatherView(inflater, container, this)
        return this.view?.root
    }

    override fun onResume() {
        super.onResume()
        this.view?.onResume()
    }
}