package fr.lvmvrquxl.thekolab.utils

import com.google.android.material.dialog.MaterialAlertDialogBuilder

internal object DialogUtils {
    internal fun notCancelable(dialogBuilder: MaterialAlertDialogBuilder) =
        this.show(dialogBuilder.setCancelable(false))

    private fun show(dialogBuilder: MaterialAlertDialogBuilder) = dialogBuilder.show()
}