package com.sukajee.tallycounter.ui

import androidx.navigation.NavDirections
import com.sukajee.tallycounter.NavGraphDirections

public class MainFragmentDirections private constructor() {
  public companion object {
    public fun actionGlobalResetConfirmationDialog(): NavDirections =
        NavGraphDirections.actionGlobalResetConfirmationDialog()

    public fun actionGlobalTargetEntryDialog(): NavDirections =
        NavGraphDirections.actionGlobalTargetEntryDialog()

    public fun actionGlobalStepsEntryDialog(): NavDirections =
        NavGraphDirections.actionGlobalStepsEntryDialog()
  }
}
