package com.kadence_test_helper.help

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

open abstract class AbstractBaseRun<T : Activity> {


//    @Rule
//    @JvmField
//    abstract var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    abstract var rule: ActivityTestRule<T>
    abstract var act: T



    @Before
    fun before() {

        act = Help.getCurrentActivity() as T
        Thread.sleep(1000)
    }


    @After
    fun after() {
        sleepForever()
    }


    abstract fun alert(title: String, msg: String)
//    {
////        act.runOnUiThread {
////            act.alert("", "Error from test")
////        }
//    }

    abstract fun toast(msg: String)
//    {
//        act.runOnUiThread {
//            Log.d("Test",msg)
//            toast(act, msg)
//        }
//    }

    /**
     * Run function body and show error alert if detect exception.
     */

    fun runCatchingTest(function: suspend CoroutineScope.() -> Unit) = runBlocking {

        val res = kotlin.runCatching {
            function.invoke(this)
        }

        if (res.isFailure) {
            res.exceptionOrNull()?.printStackTrace()
            alert("", "Error from test")
        }

    }

}