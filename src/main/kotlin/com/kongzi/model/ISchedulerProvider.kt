package com.kongzi.model
import io.reactivex.Scheduler

public interface ISchedulerProvider {
    fun computation(): Scheduler
    fun ui(): Scheduler
}