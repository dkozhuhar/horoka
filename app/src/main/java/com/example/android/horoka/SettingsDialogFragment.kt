package com.example.android.horoka

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders

class SettingsDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val viewModel: HorokaViewModel =
                ViewModelProviders.of(this, HorokaViewModel.Factory(this.activity!!.application))
                    .get(HorokaViewModel::class.java)
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.settings_dialog,null)
            val whoLovesEdited = dialogView.findViewById<EditText>(R.id.who_loves_value)
            whoLovesEdited.setText(viewModel.getNotificationTitle(it))

            builder.setView(dialogView)
                .setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, id ->
                    viewModel.setNotificationTitle(it,whoLovesEdited.text.toString())
                })
                .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}