package fr.lvmvrquxl.thekolab.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import fr.lvmvrquxl.thekolab.base.BaseView
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarWeatherFragmentBinding
import fr.lvmvrquxl.thekolab.utils.Strings

class HomeToolbarWeatherView(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?,
    private val fragment: HomeToolbarWeatherFragment
) : BaseView<HomeToolbarWeatherFragmentBinding>() {
    init {
        this.bindViews()
    }

    override fun onResume() = fragment.context?.let {
        super.viewBinding?.weatherLocationCity?.text = Strings.paris(it)
        super.viewBinding?.weatherLocationCountry?.text = Strings.france(it)
        super.viewBinding?.weatherDegreeNumber?.text = Strings.x(it)
        super.viewBinding?.weatherDescription?.text = Strings.lightRain(it)
    }

    private fun bindViews() {
        val attachToParent = false
        super.viewBinding =
            HomeToolbarWeatherFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }
}