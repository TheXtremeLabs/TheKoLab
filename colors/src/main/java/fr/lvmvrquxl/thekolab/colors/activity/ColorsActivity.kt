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

    override fun initView() = ColorsView.observe(this)

    override fun initViewModel() {
        this.viewModel = ColorsViewModel.instance.apply { this.observe(this@ColorsActivity) }
    }

    override fun onBackPressed() {
        this.viewModel?.onBackPressed()
    }

    override fun onDestroy() {
        this.destroyViewModel()
        super.onDestroy()
    }

    override fun onEndOfInit() {
        this.observeViewModelState()
    }

    private fun destroyViewModel() {
        this.viewModel = null
    }

    private fun observeViewModelState() =
        this.viewModel?.state?.observe(this) { state: ColorsState ->
            if (ColorsState.CLOSABLE == state) super.onBackPressed()
        }
}