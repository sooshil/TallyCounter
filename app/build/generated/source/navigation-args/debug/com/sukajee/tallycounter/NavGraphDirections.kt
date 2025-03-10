package com.sukajee.tallycounter

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class NavGraphDirections private constructor() {
  public companion object {
    public fun actionGlobalResetConfirmationDialog(): NavDirections =
        ActionOnlyNavDirections(R.id.action_global_resetConfirmationDialog)

    public fun actionGlobalTargetEntryDialog(): NavDirections =
        ActionOnlyNavDirections(R.id.action_global_targetEntryDialog)

    public fun actionGlobalStepsEntryDialog(): NavDirections =
        ActionOnlyNavDirections(R.id.action_global_stepsEntryDialog)
  }
}
