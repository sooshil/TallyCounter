package com.sukajee.tallycounter.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.sukajee.tallycounter.R

/**
 * Created by Sushil Kafle on 16,October,2021
 */

class TargetEntryDialog : DialogFragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.entry_dialog_view, null)
        val editText = view.findViewById<EditText>(R.id.editTextTarget)
        if (viewModel.setTarget != Int.MAX_VALUE) editText.setText(viewModel.setTarget.toString())

        val dialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle(R.string.target_entry_dialog_title)
            .setMessage(R.string.target_entry_dialog_message)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.ok, null)
            .setCancelable(false)
            .create()
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            var wantToCloseDialog = false
            //val til = view.findViewById<TextInputLayout>(R.id.til)
            if (editText.text?.trim()?.isBlank() == true) {
                editText.error = getString(R.string.enter_target_count)
            } else if ((editText.text?.toString()?.toInt() ?: 100000) > 99999) {
                editText.error = getString(R.string.value_criteria, MAX_COUNT + 1, MAX_COUNT + 1)
            } else if (editText.text?.toString()?.toInt() == viewModel.setTarget) {
                editText.error = getString(R.string.new_value_same_as_old)
            } else {
                wantToCloseDialog = true
                viewModel.onTargetSet(editText.text.trim().toString().toInt())
            }
            if (wantToCloseDialog) dismiss()
        }
        return dialog
    }
}
