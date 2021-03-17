package fr.lvmvrquxl.thekolab.colors.activity

import fr.lvmvrquxl.thekolab.colors.view.ColorsView
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsViewModel
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.viewmodel.StateManager

/**
 * Activity of the colors feature.
 *
 * @since 1.0.0
 */
class ColorsActivity : Activity() {
    companion object {
        /**
         * Class of the colors activity.
         *
         * @since 2.0.0
         */
        val javaClass: Class<ColorsActivity> = ColorsActivity::class.java
    }

    private var viewModel: ColorsViewModel? = null

    override fun initView() = ColorsView.observe(super.reference)

    override fun initViewModel() {
        this.viewModel = ColorsViewModel.instance.apply { this.observe(super.reference) }
    }

    override fun observeViewModelState() {
        this.viewModel?.state?.observe(this) { state: String ->
            if (StateManager.CLOSE == state) super.onBackPressed()
        }
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
}