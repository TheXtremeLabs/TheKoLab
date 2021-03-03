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

/**
 * Toolbar's weather view.
 *
 * @param inflater The object that can be used to inflate any views in the fragment
 * @param container Parent view that the fragment's UI should be attached to
 * @param activity Toolbar's weather activity
 *
 * @since 1.0.0
 *
 * @see [Activity]
 * @see [FragmentView]
 * @see [LayoutInflater]
 * @see [ToolbarWeatherFragmentBinding]
 * @see [ViewGroup]
 */
internal class ToolbarWeatherView private constructor(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?,
    private val activity: Activity
) : FragmentView<ToolbarWeatherFragmentBinding>() {
    companion object {
        /**
         * Create a new instance of toolbar's weather view.
         *
         * @param inflater The object that can be used to inflate any views in the fragment
         * @param container Parent view that the fragment's UI should be attached to
         * @param activity Toolbar's weather activity
         *
         * @return New instance of toolbar's weather view
         *
         * @since 1.0.0
         *
         * @see [Activity]
         * @see [FragmentView]
         * @see [LayoutInflater]
         * @see [ToolbarWeatherFragmentBinding]
         * @see [ViewGroup]
         */
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup?,
            activity: Activity
        ): FragmentView<ToolbarWeatherFragmentBinding> =
            ToolbarWeatherView(inflater, container, activity)
    }

    private var presenter: Presenter? = null

    override fun bindView() {
        val attachToParent = false
        super.viewBinding =
            ToolbarWeatherFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }

    override fun onDestroyView() {
        this.presenter = null
        super.onDestroyView()
    }

    override fun onPause() = this.presenter?.cancelCoroutines()

    override fun onResume() = this.presenter?.startBackgroundCoroutines()

    override fun onStart() {
        this.initPresenter()
        super.onStart()
    }

    private fun initPresenter() {
        val callback: ToolbarWeatherCallback = this.toolbarWeatherCallback()
        this.presenter = ToolbarWeatherPresenter.create(this.activity, callback)
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
        val animationDuration: Int =
            this.activity.resources.getInteger(android.R.integer.config_mediumAnimTime)
        super.viewBinding?.weatherInfo?.apply {
            this.alpha = 0f
            this.visibility = View.VISIBLE
            this.animate()
                .alpha(1f)
                .setDuration(animationDuration.toLong())
                .setListener(null)
        }
        super.viewBinding?.weatherProgressBar?.apply {
            this.animate()
                .alpha(0f)
                .setDuration(animationDuration.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        this@apply.visibility = View.GONE
                    }
                })
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