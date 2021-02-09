package fr.lvmvrquxl.thekolab.shared.utils

import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Utilities for managing dialogs.
 *
 * @since 1.0.0
 */
internal object DialogUtils {
    /**
     * Show a dialog that can't be cancel.
     *
     * @since 1.0.0
     *
     * @see [AlertDialog]
     * @see [MaterialAlertDialogBuilder]
     */
    fun notCancelable(dialogBuilder: MaterialAlertDialogBuilder): AlertDialog =
        this.show(dialogBuilder.setCancelable(false))

    private fun show(dialogBuilder: MaterialAlertDialogBuilder): AlertDialog = dialogBuilder.show()
}