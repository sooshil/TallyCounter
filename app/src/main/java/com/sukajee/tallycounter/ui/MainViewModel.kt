package com.sukajee.tallycounter.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukajee.tallycounter.TallyEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

/**
 * Created by Sushil Kafle on 16,October,2021
 */

const val MAX_COUNT = 99999

class MainViewModel : ViewModel() {

    private val tallyEventsChannel = Channel<TallyEvents>()
    val tallyEvents = tallyEventsChannel.receiveAsFlow()

    var currentCount = 0
    var setSteps = 1
    var setTarget = Int.MAX_VALUE
    var resetVisible = MutableLiveData(false)

    fun onPlusClick() = viewModelScope.launch {
        if (currentCount > MAX_COUNT) {
            tallyEventsChannel.send(TallyEvents.NotifyMaxCountReached)
            return@launch
        }
        if (MAX_COUNT - currentCount < setSteps) {
            tallyEventsChannel.send(TallyEvents.NotifyThisActionExceedsTarget(isMax = true))
            return@launch
        }
        currentCount += setSteps
        if (currentCount != 0) resetVisible.postValue(true) else resetVisible.postValue(false)
        handleTargetReach()
        tallyEventsChannel.send(TallyEvents.UpdateCount(currentCount))
    }

    fun onMinusClick() = viewModelScope.launch {
        if (currentCount < -MAX_COUNT) {
            tallyEventsChannel.send(TallyEvents.NotifyMinCountReached)
            return@launch
        }
        if (currentCount < 0 && (MAX_COUNT + currentCount < setSteps)) {
            tallyEventsChannel.send(TallyEvents.NotifyThisActionExceedsTarget(isMax = false))
            return@launch
        }
        currentCount -= setSteps
        if (currentCount != 0) resetVisible.postValue(true) else resetVisible.postValue(false)
        handleTargetReach()
        tallyEventsChannel.send(TallyEvents.UpdateCount(currentCount))
    }

    suspend fun handleTargetReach() {
        if (currentCount == setTarget) {
            val digits = currentCount.absoluteValue.toString().length
            tallyEventsChannel.send(TallyEvents.ShowHideTargetReachedCircle(true, digits))
        } else {
            tallyEventsChannel.send(TallyEvents.ShowHideTargetReachedCircle(false))
        }
    }

    fun onResetClick() = viewModelScope.launch {
        tallyEventsChannel.send(TallyEvents.ShowResetConfirmationScreen)
    }

    fun onTargetClick() = viewModelScope.launch {
        tallyEventsChannel.send(TallyEvents.ShowTargetEntryScreen)
    }

    fun onStepsClick() = viewModelScope.launch {
        tallyEventsChannel.send(TallyEvents.ShowStepsEntryScreen)
    }

    fun onResetConfirmed(isCheckedTarget: Boolean, isCheckedSteps: Boolean) = viewModelScope.launch {
        currentCount = 0
        if(isCheckedTarget) {
            setTarget = Int.MAX_VALUE
            onTargetSet(setTarget)
        }
        if(isCheckedSteps) {
            setSteps = 1
        }
        resetVisible.postValue(false)
        tallyEventsChannel.send(TallyEvents.ShowHideTargetReachedCircle(false))
        tallyEventsChannel.send(TallyEvents.UpdateCount(currentCount))
    }

    fun onTargetSet(target: Int) = viewModelScope.launch {
        setTarget = target
        if(target > MAX_COUNT && target != Int.MAX_VALUE) {
            tallyEventsChannel.send(TallyEvents.NotifyTargetCanNotBeSet(target))
        } else {
            tallyEventsChannel.send(TallyEvents.ShowTargetCountDetails(target))
            tallyEventsChannel.send(TallyEvents.ShowHideTargetReachedCircle(false))
        }
    }

    fun onStepsSet(steps: Int) {
        setSteps = steps
    }
}