package fr.lvmvrquxl.thekolab.shared.utils

import android.content.Context
import android.widget.Toast

/**
 * Utilities for managing toasts.
 *
 * @since 1.0.0
 */
internal object ToastUtils {
    private const val SHORT: Int = Toast.LENGTH_SHORT

    /**
     * Show a short toast with given text in given context.
     *
     * @param context Context to show the toast in
     * @param text Text to display
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    internal fun short(context: Context, text: String) = Toast.makeText(context, text, SHORT).show()
}