package fr.lvmvrquxl.thekolab.home.view

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import fr.lvmvrquxl.thekolab.base.BaseView
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarWeatherFragmentBinding
import fr.lvmvrquxl.thekolab.home.presenter.HomeToolbarWeatherPresenter
import fr.lvmvrquxl.thekolab.utils.Strings

class HomeToolbarWeatherView(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?,
    private val fragment: HomeToolbarWeatherFragment
) : BaseView<HomeToolbarWeatherFragmentBinding>() {
    val activity: Activity?
        get() = this.fragment.activity

    private var presenter: HomeToolbarWeatherPresenter? = null

    init {
        this.bindViews()
        this.presenter = HomeToolbarWeatherPresenter.build(this)
    }

    override fun onResume() {
        this.presenter?.launchLocationRetriever()
        this.initViews()
    }

    fun setLocationCity(city: String) {
        super.viewBinding?.weatherLocationCity?.apply { this.text = city }
    }

    fun setLocationCountry(country: String) {
        super.viewBinding?.weatherLocationCountry?.apply { this.text = country }
    }

    private fun initViews() = fragment.context?.let { context: Context ->
        super.viewBinding?.weatherDegreeNumber?.text = Strings.x(context)
        super.viewBinding?.weatherDescription?.text = Strings.lightRain(context)
    }

    private fun bindViews() {
        val attachToParent = false
        super.viewBinding =
            HomeToolbarWeatherFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }
}