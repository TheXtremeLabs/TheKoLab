package fr.lvmvrquxl.thekolab.colors.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.lvmvrquxl.thekolab.colors.view.ColorsView
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsState
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.LifecycleObserver

/**
 * Activity of the colors feature.
 *
 * @since 1.0.0
 *
 * @see AppCompatActivity
 */
class ColorsActivity : AppCompatActivity() {
    private val stateObserver: Observer<ColorsState>
        get() = Observer { state: ColorsState ->
            if (ColorsState.CLOSABLE == state) super.onBackPressed()
        }

    private val view: LifecycleObserver
        get() = ColorsView.create(this)

    private val viewModel: ColorsViewModel
        get() = ColorsViewModel.instance(this).apply {
            this.state.observe(this@ColorsActivity, this@ColorsActivity.stateObserver)
        }

    init {
        this.lifecycle.addObserver(this.view)
        this.lifecycle.addObserver(this.viewModel)
    }

    override fun onBackPressed() = this.viewModel.onBackPressed()
}