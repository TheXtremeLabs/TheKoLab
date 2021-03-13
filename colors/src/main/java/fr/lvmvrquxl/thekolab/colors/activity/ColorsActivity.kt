package fr.lvmvrquxl.thekolab.colors.activity

import fr.lvmvrquxl.thekolab.colors.view.ColorsView
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsState
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsViewModel
import fr.lvmvrquxl.thekolab.shared.activity.Activity

/**
 * Activity of the colors feature.
 *
 * @since 1.0.0
 */
class ColorsActivity : Activity() {
    private var viewModel: ColorsViewModel? = null

    // TODO: Share this method in parent with other activities
    init {
        this.initView()
        this.initViewModel()
    }

    override fun onBackPressed() {
        this.viewModel?.onBackPressed()
    }

    override fun onDestroy() {
        this.destroyViewModel()
        super.onDestroy()
    }

    private fun destroyViewModel() {
        this.viewModel = null
    }

    private fun initView() = ColorsView.observe(this)

    private fun initViewModel() {
        this.viewModel = ColorsViewModel.instance.apply { this.observe(this@ColorsActivity) }
        this.observeViewModelState()
    }

    private fun observeViewModelState() =
        this.viewModel?.state?.observe(this) { state: ColorsState ->
            if (ColorsState.CLOSABLE == state) super.onBackPressed()
        }
}