package fr.lvmvrquxl.thekolab.colors.activity

import androidx.lifecycle.Observer
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
    private val stateObserver: Observer<ColorsState>
        get() = Observer { state: ColorsState ->
            if (ColorsState.CLOSABLE == state) super.onBackPressed()
        }

    private val viewModel: ColorsViewModel
        get() = ColorsViewModel.instance(this).apply {
            this.state.observe(this@ColorsActivity, this@ColorsActivity.stateObserver)
        }

    init {
        ColorsView.observe(this)
        this.lifecycle.addObserver(this.viewModel)
    }

    override fun onBackPressed() = this.viewModel.onBackPressed()
}