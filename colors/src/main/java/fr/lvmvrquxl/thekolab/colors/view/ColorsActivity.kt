package fr.lvmvrquxl.thekolab.colors.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsState
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

/**
 * Activity of the colors feature.
 *
 * @since 1.0.0
 *
 * @see AppCompatActivity
 */
class ColorsActivity : AppCompatActivity() {
    private var view: ActivityView<ColorsActivityBinding>? = null
    private var viewModel: ColorsViewModel? = null

    override fun onBackPressed() {
        this.viewModel?.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.createView()
        this.createViewModel()
        this.observeState()
    }

    override fun onDestroy() {
        this.destroyViewModel()
        this.destroyView()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        this.startView()
        this.startViewModel()
    }

    private fun createView() {
        this.view = ColorsView.create(this).apply {
            this.onCreate()
            super.setContentView(this.root)
        }
    }

    private fun createViewModel() {
        this.viewModel = ColorsViewModel.instance(this)
    }

    private fun destroyView() {
        this.view?.onDestroy()
        this.view = null
    }

    private fun destroyViewModel() {
        this.viewModel?.onDestroy()
        this.viewModel = null
    }

    private fun observeState() = this.viewModel?.state?.observe(this) { state: ColorsState ->
        if (ColorsState.CLOSABLE == state) super.onBackPressed()
    }

    private fun startView() = this.view?.onStart()

    private fun startViewModel() = this.viewModel?.onStart()
}