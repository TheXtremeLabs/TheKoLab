package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsActionState
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView

internal abstract class ColorsAnimatedView(private val activity: AppCompatActivity) : AnimatedView {
    protected val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)
    protected var color: Color? = null

    override fun onCreate() {
        this.observeColor()
        this.observeActionState()
    }

    override fun onDestroy() {
        this.color = null
    }

    private fun observeActionState() =
        this.viewModel.actionState.observe(this.activity) { state: ColorsActionState ->
            when (state) {
                ColorsActionState.START -> this.showStartAnimation()
                ColorsActionState.UPDATE -> this.showUpdateAnimation()
                ColorsActionState.EXIT -> this.showExitAnimation()
                else -> {
                }
            }
        }

    private fun observeColor() = this.viewModel.color.observe(this.activity) { color: Color ->
        this.color = color
    }
}