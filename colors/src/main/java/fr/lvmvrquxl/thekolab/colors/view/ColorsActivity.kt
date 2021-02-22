package fr.lvmvrquxl.thekolab.colors.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsState
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
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
    private var viewModel: IColorsViewModel? = null

    override fun onBackPressed() {
        this.viewModel?.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initView()
        this.initViewModel()
        this.observeState()
        this.setContentView()
    }

    override fun onDestroy() {
        this.destroyViewModel()
        this.destroyView()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        this.view?.onStart()
        this.viewModel?.onStart()
    }

    private fun destroyView() {
        this.view?.onDestroy()
        this.view = null
    }

    private fun destroyViewModel() {
        this.viewModel?.onDestroy()
        this.viewModel = null
    }

    private fun initView() {
        this.view = ColorsView.create(this)
        this.view?.onCreate()
    }

    private fun initViewModel() {
        this.viewModel = IColorsViewModel.instance(this)
    }

    private fun observeState() = this.viewModel?.state?.observe(this) { state: ColorsState ->
        if (ColorsState.CLOSABLE == state) super.onBackPressed()
    }

    private fun setContentView() = this.view?.let { view: ActivityView<ColorsActivityBinding> ->
        super.setContentView(view.root)
    }
}