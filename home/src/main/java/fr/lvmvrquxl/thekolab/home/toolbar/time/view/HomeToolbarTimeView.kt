package fr.lvmvrquxl.thekolab.home.toolbar.time.view

import android.view.LayoutInflater
import android.view.ViewGroup
import fr.lvmvrquxl.thekolab.home.databinding.HomeToolbarTimeFragmentBinding
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

    override fun onPause() = this.presenter?.cancelTimeUpdaterJob()

    override fun onResume() {
        this.presenter?.launchTimeUpdater()
        this.initDateText()
    }

    /**
     * Update toolbar's time text.
     *
     * This method updates the time displayed in the toolbar.
     *
     * @param timeText New time to display
     *
     * @since 0.1.3
     */
    internal fun updateTimeText(timeText: String) {
        super.viewBinding?.toolbarTime?.text = timeText
    }

    private fun bindViews() {
        val attachToParent = false
        super.viewBinding =
            HomeToolbarTimeFragmentBinding.inflate(this.inflater, this.container, attachToParent)
    }

    private fun initDateText() {
        super.viewBinding?.toolbarDate?.text = this.presenter?.getCurrentDate()
    }
}