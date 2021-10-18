package com.sukajee.tallycounter

/**
 * Created by Sushil Kafle on 16,October,2021
 */

sealed class TallyEvents {
    object NotifyMaxCountReached : TallyEvents()
    object NotifyMinCountReached : TallyEvents()
    object ShowStepsEntryScreen : TallyEvents()
    object ShowResetConfirmationScreen : TallyEvents()
    object ShowTargetEntryScreen : TallyEvents()
    data class NotifyThisActionExceedsTarget(val isMax: Boolean) : TallyEvents()
    data class ShowHideTargetReachedCircle(val showGreenCircle: Boolean, val digits: Int = 2) : TallyEvents()
    data class UpdateCount(val currentCount: Int): TallyEvents()
    data class ShowTargetCountDetails(val target: Int) : TallyEvents()
    data class NotifyTargetCanNotBeSet(val target: Int) : TallyEvents()
}
