package com.kadence_test_helper.help

import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun Any.sleepForever() {
    Thread.sleep(50000000000)
}

fun Any.childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {

    return object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) {
            description?.appendText("Child at position $position in parent ")
            parentMatcher.describeTo(description)
        }


        public override fun matchesSafely(view: View): Boolean {
            val parent = view.parent
            return parent is ViewGroup && parentMatcher.matches(parent)
                    && view == parent.getChildAt(position)
        }
    }
}