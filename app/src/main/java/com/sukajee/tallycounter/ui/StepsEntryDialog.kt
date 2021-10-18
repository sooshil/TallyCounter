package com.sukajee.tallycounter.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.sukajee.tallycounter.R
import kotlin.math.absoluteValue

/**
 * Created by Sushil Kafle on 17,October,2021
 */

class StepsEntryDialog : DialogFragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.entry_dialog_view, null)
        val editText = view.findViewById<EditText>(R.id.editTextTarget)
        if(viewModel.setSteps != 1) editText.setText(viewModel.setSteps.toString())

        val dialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle(R.string.steps)
            .setMessage(R.string.steps_entry_dialog_message)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.ok, null)
            .setCancelable(false)
            .create()
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            var wantToCloseDialog = false
            val steps = editText.text.trim().toString().let {
                if (it.isNotEmpty()) it.toInt().absoluteValue else 1
            }
            if (editText.text?.isBlank() == true) {
                editText.error = getString(R.string.enter_steps)
            } else if (editText.text?.toString()?.toInt() ?: 100000 > 99999) {
                editText.error = getString(R.string.value_criteria, MAX_COUNT + 1, MAX_COUNT + 1)
            } else if (viewModel.setTarget != Int.MAX_VALUE && steps > viewModel.setTarget) {
                editText.error = getString(R.string.steps_cannot_be_greater)
            } else {
                wantToCloseDialog = true
                viewModel.onStepsSet(steps)
            }
            if (wantToCloseDialog) dismiss()
        }
        return dialog
    }
}