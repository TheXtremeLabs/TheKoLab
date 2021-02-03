package fr.lvmvrquxl.thekolab.home.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import fr.lvmvrquxl.thekolab.base.BaseView
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarWeatherFragmentBinding
import fr.lvmvrquxl.thekolab.home.presenter.HomeToolbarWeatherPresenter

internal class HomeToolbarWeatherView(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?,
    private val fragment: HomeToolbarWeatherFragment
) : BaseView<HomeToolbarWeatherFragmentBinding>() {
    internal val activity: Activity?
        get() = this.fragment.activity

    private var presenter: HomeToolbarWeatherPresenter? = null

    init {
        this.bindViews()
        this.presenter = HomeToolbarWeatherPresenter.build(this)
    }

    override fun onResume() = this.presenter?.launchLocationRetriever()

    internal fun setDescription(description: String) {
        super.viewBinding?.weatherDescription?.text = description
    }

    internal fun setDegreeNumber(degree: Double) {
        super.viewBinding?.weatherDegreeNumber?.text = "${degree.toInt()}"
    }

    internal fun setLocationCity(city: String) {
        super.viewBinding?.weatherLocationCity?.apply { this.text = city }
    }

    internal fun setLocationCountry(country: String) {
        super.viewBinding?.weatherLocationCountry?.apply { this.text = country }
    }

    private fun bindViews() {
        val attachToParent = false
        super.viewBinding =
            HomeToolbarWeatherFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }
}