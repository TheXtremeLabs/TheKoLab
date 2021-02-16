package fr.lvmvrquxl.thekolab.colors.repository

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

internal object ColorsRepository : IColorsRepository {
    override val firstColor: Color
        get() = when (null == this.colorBackup) {
            true -> this.colors.first()
            else -> this.colorBackup!!
        }
    override val randomColor: Color
        get() = this.colors.random()

    private var colorBackup: Color? = null
    private val colors: MutableList<Color> = mutableListOf()

    override fun backupColor(color: Color) {
        this.colorBackup = color
    }

    fun withContext(context: Context): IColorsRepository {
        this.colors.clear()
        this.addColors(context)
        return this
    }

    private fun addColors(context: Context) {
        this.colors.add(Color(StringUtils.white(context)) { c: Context -> ColorUtils.white(c) })
        this.colors.add(Color(StringUtils.orange(context)) { c: Context -> ColorUtils.orange(c) })
        this.colors.add(Color(StringUtils.purple(context)) { c: Context -> ColorUtils.purple(c) })
        this.colors.add(Color(StringUtils.blue(context)) { c: Context -> ColorUtils.blue(c) })
        this.colors.add(Color(StringUtils.red(context)) { c: Context -> ColorUtils.red(c) })
    }
}