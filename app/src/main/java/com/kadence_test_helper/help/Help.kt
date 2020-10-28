package com.kadence_test_helper.help

import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.app.Activity
import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.Stage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



object Help {

    @Throws(Throwable::class)
    fun getCurrentActivity(): Activity? {
        Thread.sleep(100)
        getInstrumentation().waitForIdleSync()
        val activity = arrayOfNulls<Activity>(1)



        GlobalScope.launch(Dispatchers.Main) {
            val activities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            activity[0] = Iterables.getOnlyElement(activities)
        }



        Thread.sleep(100)
        return activity[0]
    }

}