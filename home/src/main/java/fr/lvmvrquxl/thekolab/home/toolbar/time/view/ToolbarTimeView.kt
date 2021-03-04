package fr.lvmvrquxl.thekolab.home.toolbar.time.view

import android.view.LayoutInflater
import android.view.ViewGroup
import fr.lvmvrquxl.thekolab.home.databinding.ToolbarTimeFragmentBinding
import fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.ToolbarTimeCallback
import fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.ToolbarTimePresenter
import fr.lvmvrquxl.thekolab.shared.presenter.Presenter
import fr.lvmvrquxl.thekolab.shared.view.FragmentView

/**
 * View of the toolbar's time.
 *
 * @param inflater The object that can be used to inflate any views in the fragment
 * @param container Parent view that the fragment's UI should be attached to
 *
 * @since 1.0.0
 *
 * @see [FragmentView]
 * @see [LayoutInflater]
 * @see [ToolbarTimeFragmentBinding]
 * @see [ViewGroup]
 */
internal class ToolbarTimeView private constructor(
    private val inflater: LayoutInflater,
    private val container: ViewGroup?
) : FragmentView<ToolbarTimeFragmentBinding>() {
    companion object {
        /**
         * Create a new instance of the toolbar's time view.
         *
         * @param inflater The object that can be used to inflate any views in the fragment
         * @param container Parent view that the fragment's UI should be attached to
         *
         * @return New instance of the toolbar's time view
         *
         * @since 1.0.0
         *
         * @see [FragmentView]
         * @see [LayoutInflater]
         * @see [ToolbarTimeFragmentBinding]
         * @see [ViewGroup]
         */
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup?
        ): FragmentView<ToolbarTimeFragmentBinding> = ToolbarTimeView(inflater, container)
    }

    private var presenter: Presenter? = null

    override fun bindView() {
        val attachToParent = false
        super.viewBinding =
            ToolbarTimeFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }

    override fun onDestroyView() {
        this.presenter = null
        super.onDestroyView()
    }

    override fun onPause() {
        this.presenter?.cancelCoroutines()
        super.onPause()
    }

    override fun onResume() {
        this.presenter?.startBackgroundCoroutines()
        super.onResume()
    }

    override fun onStart() {
        this.initPresenter()
        super.onStart()
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