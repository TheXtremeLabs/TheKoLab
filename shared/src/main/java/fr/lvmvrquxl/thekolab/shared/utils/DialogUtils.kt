package fr.lvmvrquxl.thekolab.shared.utils

import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

internal object DialogUtils {
    internal fun notCancelable(dialogBuilder: MaterialAlertDialogBuilder): AlertDialog =
        this.show(dialogBuilder.setCancelable(false))

    private fun show(dialogBuilder: MaterialAlertDialogBuilder): AlertDialog = dialogBuilder.show()
}