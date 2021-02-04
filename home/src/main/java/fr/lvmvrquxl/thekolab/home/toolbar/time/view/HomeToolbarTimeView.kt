package fr.lvmvrquxl.thekolab.home.toolbar.time.view

import android.view.LayoutInflater
import android.view.ViewGroup
import fr.lvmvrquxl.thekolab.home.databinding.HomeToolbarTimeFragmentBinding
import fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.HomeToolbarTimeCallback
import fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.HomeToolbarTimePresenter
import fr.lvmvrquxl.thekolab.shared.view.BaseView

/**
 * View of the home toolbar's time.
 *
 * This view is managing the elements of the toolbar's time fragment.
 *
 * @param inflater The object that can be used to inflate any views in the fragment
 * @param container Parent view that the fragment's UI should be attached to
 *
 * @constructor Bind view and init its presenter [HomeToolbarTimePresenter]
 *
 * @since 0.1.3
 * @see [HomeToolbarTimePresenter]
 */
internal class HomeToolbarTimeView(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?
) : BaseView<HomeToolbarTimeFragmentBinding>() {
    companion object {
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup?
        ): BaseView<HomeToolbarTimeFragmentBinding> = HomeToolbarTimeView(inflater, container)
    }

    private var presenter: HomeToolbarTimePresenter? = null

    init {
        this.bindViews()
        this.initPresenter()
    }

    override fun onDestroyView() {
        super.onDestroy()
        this.presenter?.cancelCoroutines()
        this.presenter = null
    }

    override fun onPause() = this.presenter?.cancelCoroutines()

    override fun onResume() {
        this.presenter?.startBackgroundCoroutines()
    }

    private fun bindViews() {
        val attachToParent = false
        super.viewBinding =
            HomeToolbarTimeFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }

    private fun homeToolbarTimeCallback(): HomeToolbarTimeCallback =
        object : HomeToolbarTimeCallback {
            private val view: HomeToolbarTimeFragmentBinding? =
                super@HomeToolbarTimeView.viewBinding

            override fun updateDate(date: String) {
                this.view?.toolbarDate?.text = date
            }

            override fun updateTime(time: String) {
                this.view?.toolbarTime?.text = time
            }
        }

    private fun initPresenter() {
        val callback: HomeToolbarTimeCallback = this.homeToolbarTimeCallback()
        this.presenter = HomeToolbarTimePresenter.create(callback)
    }
}