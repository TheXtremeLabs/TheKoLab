package fr.lvmvrquxl.thekolab.home.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
    private var shortAnimationDuration: Int = 0

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
            HomeToolbarWeatherFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }
}