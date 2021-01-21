package fr.lvmvrquxl.thekolab.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarTimeFragmentBinding
import fr.lvmvrquxl.thekolab.home.presenter.HomeToolbarTimePresenter

class HomeToolbarTimeView(private val inflater: LayoutInflater, private val container: ViewGroup?) {
    private var viewBinding: HomeToolbarTimeFragmentBinding? = null
    private var presenter: HomeToolbarTimePresenter? = null

    init {
        this.bindViews()
        this.presenter = HomeToolbarTimePresenter.build(this)
    }

    fun onDestroyView() {
        this.presenter?.cancelCoroutineScope()
        this.presenter = null
        this.viewBinding = null
    }

    fun onPause() = this.presenter?.cancelTimeUpdaterJob()

    fun onResume() {
        this.presenter?.launchTimeUpdater()
        this.initDateText()
    }

    fun root(): View? = this.viewBinding?.root

    private fun bindViews() {
        val attachToParent = false
        this.viewBinding =
            HomeToolbarTimeFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }

    private fun initDateText() {
        this.viewBinding?.toolbarDate?.text = this.presenter?.getCurrentDate()
    }

    fun updateTimeText(timeText: String) {
        this.viewBinding?.toolbarTime?.text = timeText
    }
}