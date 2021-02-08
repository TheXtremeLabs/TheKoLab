package fr.lvmvrquxl.thekolab.home.toolbar.weather.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarWeatherFragmentBinding
import fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter.ToolbarWeatherCallback
import fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter.ToolbarWeatherPresenter
import fr.lvmvrquxl.thekolab.shared.presenter.Presenter
import fr.lvmvrquxl.thekolab.shared.view.FragmentView

internal class ToolbarWeatherView(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?,
    private val fragment: ToolbarWeatherFragment
) : FragmentView<ToolbarWeatherFragmentBinding>() {
    companion object {
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup?,
            fragment: ToolbarWeatherFragment
        ): FragmentView<ToolbarWeatherFragmentBinding> =
            ToolbarWeatherView(inflater, container, fragment)
    }

    private val activity: Activity?
        get() = this.fragment.activity
    private var presenter: Presenter? = null

    override fun onCreateView() = this.bindViews()

    override fun onDestroyView() {
        this.presenter = null
        super.onDestroyView()
    }

    override fun onPause() = this.presenter?.cancelCoroutines()

    override fun onResume() = this.presenter?.startBackgroundCoroutines()

    override fun onStart() = this.initPresenter()

    private fun bindViews() {
        val attachToParent = false
        super.viewBinding =
            ToolbarWeatherFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }

    private fun initPresenter() {
        val callback: ToolbarWeatherCallback = this.toolbarWeatherCallback()
        this.presenter = this.activity?.let { activity: Activity ->
            ToolbarWeatherPresenter.create(activity, callback)
        }
    }

    private fun setCondition(description: String) {
        super.viewBinding?.weatherCondition?.text = description
    }

    private fun setDegreeNumber(degree: Double) {
        super.viewBinding?.weatherDegreeNumber?.text = "${degree.toInt()}"
    }

    private fun setLocationCity(city: String) {
        super.viewBinding?.weatherLocationCity?.apply { this.text = city }
    }

    private fun setLocationCountry(country: String) {
        super.viewBinding?.weatherLocationCountry?.apply { this.text = country }
    }

    private fun showWeatherInfo() {
        val animationDuration: Int? = this.activity?.let { activity: Activity ->
            activity.resources.getInteger(android.R.integer.config_mediumAnimTime)
        }
        super.viewBinding?.weatherInfo?.apply {
            this.alpha = 0f
            this.visibility = View.VISIBLE
            animationDuration?.toLong()?.let { duration: Long ->
                this.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(null)
            }
        }
        super.viewBinding?.weatherProgressBar?.apply {
            animationDuration?.toLong()?.let {
                this.animate()
                    .alpha(0f)
                    .setDuration(it)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            this@apply.visibility = View.GONE
                        }
                    })
            }
        }
    }

    private fun toolbarWeatherCallback(): ToolbarWeatherCallback = object : ToolbarWeatherCallback {
        override fun showWeather() {
            this@ToolbarWeatherView.showWeatherInfo()
        }

        override fun updateWeather(weather: WeatherDTO) {
            this@ToolbarWeatherView.setLocationCity(weather.cityName)
            this@ToolbarWeatherView.setLocationCountry(weather.system.country)
            this@ToolbarWeatherView.setDegreeNumber(weather.mainData.temperature)
            this@ToolbarWeatherView.setCondition(weather.weather[0].condition)
        }
    }
}