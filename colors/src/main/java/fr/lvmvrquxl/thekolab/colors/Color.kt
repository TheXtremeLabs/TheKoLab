package fr.lvmvrquxl.thekolab.colors

import android.content.Context

internal data class Color(val name: String, val getColor: (context: Context) -> Int)
