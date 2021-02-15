package fr.lvmvrquxl.thekolab.colors.presenter

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.presenter.Presenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

internal class ColorsPresenter private constructor(private val context: Context) : Presenter() {
    companion object {
        fun create(context: Context): ColorsPresenter = ColorsPresenter(context)
    }

    private val colors: List<Color> = listOf(
        Color(StringUtils.white(this.context)) { context: Context -> ColorUtils.white(context) },
        Color(StringUtils.orange(this.context)) { context: Context -> ColorUtils.orange(context) },
        Color(StringUtils.purple(this.context)) { context: Context -> ColorUtils.purple(context) },
        Color(StringUtils.blue(this.context)) { context: Context -> ColorUtils.blue(context) },
        Color(StringUtils.red(this.context)) { context: Context -> ColorUtils.red(context) }
    )
    private var currentColor: Color = this.colors.first()
    private var nextColor: Color = this.pickRandomColor()

    fun currentColor(): Int = runBlocking(Dispatchers.Default) {
        this@ColorsPresenter.currentColor.value(this@ColorsPresenter.context)
    }

    fun currentColorName(): String = runBlocking(Dispatchers.Default) {
        this@ColorsPresenter.currentColor.name
    }

    fun nextColor(): Int = runBlocking(Dispatchers.Default) {
        this@ColorsPresenter.nextColor.value(this@ColorsPresenter.context)
    }

    fun nextColorName(): String = runBlocking(Dispatchers.Default) {
        this@ColorsPresenter.nextColor.name
    }

    fun randomizeNextColor(): ColorsPresenter = runBlocking(Dispatchers.Default) {
        this@ColorsPresenter.nextColor = this@ColorsPresenter.pickRandomColor()
        this@ColorsPresenter
    }

    fun updateCurrentColor() = runBlocking(Dispatchers.Default) {
        this@ColorsPresenter.currentColor = this@ColorsPresenter.nextColor
    }

    private fun pickRandomColor(): Color {
        var color: Color = this.colors.random()
        while (this.currentColor == color) color = this.colors.random()
        return color
    }
}