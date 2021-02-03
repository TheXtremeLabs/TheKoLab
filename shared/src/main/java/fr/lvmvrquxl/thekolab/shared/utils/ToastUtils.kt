package fr.lvmvrquxl.thekolab.shared.utils

import android.content.Context
import android.widget.Toast

internal object ToastUtils {
    private const val SHORT: Int = Toast.LENGTH_SHORT

    internal fun short(context: Context, text: String) = Toast.makeText(context, text, SHORT).show()
}