package fr.lvmvrquxl.thekolab.home.toolbar.weather.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarWeatherFragmentBinding
import fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter.ToolbarWeatherPresenter
import fr.lvmvrquxl.thekolab.shared.view.BaseView

internal class ToolbarWeatherView(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?,
    private val fragment: ToolbarWeatherFragment
) : BaseView<ToolbarWeatherFragmentBinding>() {
    internal val activity: Activity?
        get() = this.fragment.activity

    private var presenter: ToolbarWeatherPresenter? = null
    private var shortAnimationDuration: Int = 0

    override fun onCreateView() = this.bindViews()

    override fun onDestroyView() {
        this.presenter = null
        super.onDestroyView()
    }

    override fun onPause() = this.presenter?.cancelCoroutines()

    override fun onResume() = this.presenter?.startBackgroundCoroutines()

    override fun onStart() = this.initPresenter()

    internal fun setDegreeNumber(degree: Double) {
        super.viewBinding?.weatherDegreeNumber?.text = "${degree.toInt()}"
    }

    internal fun setDescription(description: String) {
        super.viewBinding?.weatherDescription?.text = description
    }

    internal fun setLocationCity(city: String) {
        super.viewBinding?.weatherLocationCity?.apply { this.text = city }
    }

    internal fun setLocationCountry(country: String) {
        super.viewBinding?.weatherLocationCountry?.apply { this.text = country }
    }

    internal fun showWeatherInfo() = this.activity?.let { activity: Activity ->
        this.shortAnimationDuration =
            activity.resources.getInteger(android.R.integer.config_mediumAnimTime)
        super.viewBinding?.weatherInfo?.apply {
            this.alpha = 0f
            this.visibility = View.VISIBLE
            this.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }
        super.viewBinding?.weatherProgressBar?.let { progressBar: ProgressBar ->
            progressBar.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        progressBar.visibility = View.GONE
                    }
                })
        }
    }

    private fun bindViews() {
        val attachToParent = false
        super.viewBinding =
            ToolbarWeatherFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }

    private fun initPresenter() {
        this.presenter = ToolbarWeatherPresenter.build(this)
    }
}