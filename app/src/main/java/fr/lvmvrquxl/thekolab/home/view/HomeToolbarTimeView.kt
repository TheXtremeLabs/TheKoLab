package fr.lvmvrquxl.thekolab.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import fr.lvmvrquxl.thekolab.base.BaseView
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarTimeFragmentBinding
import fr.lvmvrquxl.thekolab.home.presenter.HomeToolbarTimePresenter

class HomeToolbarTimeView(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?
) : BaseView<HomeToolbarTimeFragmentBinding>() {
    private var presenter: HomeToolbarTimePresenter? = null

    init {
        this.bindViews()
        this.presenter = HomeToolbarTimePresenter.build(this)
    }

    override fun onDestroyView() {
        super.onDestroy()
        this.presenter?.cancelCoroutineScope()
        this.presenter = null
    }

    fun onPause() = this.presenter?.cancelTimeUpdaterJob()

    fun onResume() {
        this.presenter?.launchTimeUpdater()
        this.initDateText()
    }

    private fun bindViews() {
        val attachToParent = false
        super.viewBinding =
            HomeToolbarTimeFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }

    private fun initDateText() {
        super.viewBinding?.toolbarDate?.text = this.presenter?.getCurrentDate()
    }

    fun updateTimeText(timeText: String) {
        super.viewBinding?.toolbarTime?.text = timeText
    }
}