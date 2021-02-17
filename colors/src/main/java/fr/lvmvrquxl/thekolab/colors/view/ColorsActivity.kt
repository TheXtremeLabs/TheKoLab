package fr.lvmvrquxl.thekolab.colors.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsActionStatus
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

class ColorsActivity : AppCompatActivity() {
    private var view: ActivityView<ColorsActivityBinding>? = null
    private var viewModel: IColorsViewModel? = null

    override fun onBackPressed() {
        this.viewModel?.exit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initView()
        this.initViewModel()
        this.observeActionStatus()
    }

    override fun onDestroy() {
        this.viewModel?.onDestroy()
        this.viewModel = null
        this.view?.onDestroy()
        this.view = null
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        this.view?.onStart()
        this.viewModel?.onStart()
    }

    private fun initView() {
        this.view = ColorsView.create(this)
        this.view?.let { view: ActivityView<ColorsActivityBinding> ->
            view.onCreate()
            super.setContentView(view.root)
        }
    }

    private fun initViewModel() {
        this.viewModel = IColorsViewModel.instance(this)
    }

    private fun observeActionStatus() =
        this.viewModel?.actionStatus?.observe(this) { status: ColorsActionStatus ->
            if (ColorsActionStatus.CLOSABLE == status) super.onBackPressed()
        }
}