package fr.lvmvrquxl.thekolab.home.toolbar.time.view

import android.view.LayoutInflater
import android.view.ViewGroup
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarTimeFragmentBinding
import fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.ToolbarTimeCallback
import fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.ToolbarTimePresenter
import fr.lvmvrquxl.thekolab.shared.view.BaseView

/**
 * View of the home toolbar's time.
 *
 * This view is managing the elements of the toolbar's time fragment.
 *
 * @param inflater The object that can be used to inflate any views in the fragment
 * @param container Parent view that the fragment's UI should be attached to
 *
 * @constructor Bind view and init its presenter [ToolbarTimePresenter]
 *
 * @since 0.1.3
 * @see [ToolbarTimePresenter]
 */
internal class ToolbarTimeView(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?
) : BaseView<ToolbarTimeFragmentBinding>() {
    companion object {
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup?
        ): BaseView<ToolbarTimeFragmentBinding> = ToolbarTimeView(inflater, container)
    }

    private var presenter: ToolbarTimePresenter? = null

    init {
        this.bindViews()
        this.initPresenter()
    }

    override fun onDestroyView() {
        this.presenter = null
        super.onDestroy()
    }

    override fun onPause() = this.presenter?.cancelCoroutines()

    override fun onResume() {
        this.presenter?.startBackgroundCoroutines()
    }

    private fun bindViews() {
        val attachToParent = false
        super.viewBinding =
            ToolbarTimeFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }

    private fun homeToolbarTimeCallback(): ToolbarTimeCallback =
        object : ToolbarTimeCallback {
            private val view: ToolbarTimeFragmentBinding? =
                super@ToolbarTimeView.viewBinding

            override fun updateDate(date: String) {
                this.view?.toolbarDate?.text = date
            }

            override fun updateTime(time: String) {
                this.view?.toolbarTime?.text = time
            }
        }

    private fun initPresenter() {
        val callback: ToolbarTimeCallback = this.homeToolbarTimeCallback()
        this.presenter = ToolbarTimePresenter.create(callback)
    }
}