package com.sukajee.counter.data.mappers

import com.sukajee.counter.data.dto.CounterDto
import com.sukajee.counter.domain.Counter

fun CounterDto.toCounter(): Counter {
    return Counter(
        id = id,
        name = name,
        currentCount = currentCount,
        target = target,
        steps = steps,
        isPinned = isPinned
    )
}

fun Counter.toCounterDto(): CounterDto {
    return CounterDto(
        id = id,
        name = name,
        currentCount = currentCount,
        target = target,
        steps = steps,
        isPinned = isPinned
    )
}