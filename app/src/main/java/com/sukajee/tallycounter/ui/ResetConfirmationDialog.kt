package com.sukajee.tallycounter.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.sukajee.tallycounter.R

/**
 * Created by Sushil Kafle on 16,October,2021
 */

class ResetConfirmationDialog : DialogFragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view = LayoutInflater.from(requireContext()).inflate(R.layout.counter_reset_checkbox, null)
        val checkBoxTarget = view.findViewById<CheckBox>(R.id.checkBoxTarget)
        val checkBoxSteps = view.findViewById<CheckBox>(R.id.checkBoxSteps)
        if (viewModel.setTarget != Int.MAX_VALUE) {
            checkBoxTarget.text = getString(R.string.reset_currently_set_target, viewModel.setTarget)
        } else {
            checkBoxTarget.visibility = View.GONE
        }
        if (viewModel.setSteps != 1) {
            checkBoxSteps.text = getString(R.string.reset_currently_set_steps, viewModel.setSteps)
        } else {
            checkBoxSteps.visibility = View.GONE
        }

        if(viewModel.setTarget == Int.MAX_VALUE && viewModel.setSteps == 1) view = null

        val dialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle(R.string.reset_confirmation_title)
            .setMessage(R.string.reset_confirmation_message)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.onResetConfirmed(checkBoxTarget.isChecked, checkBoxSteps.isChecked)
            }
            .create()
        dialog.show()

        return dialog
    }
}