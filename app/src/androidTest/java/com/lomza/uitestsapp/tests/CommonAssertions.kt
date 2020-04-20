package com.lomza.uitestsapp.tests

import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers

fun assertToolbarTitle(@IdRes toolbarId: Int,  text: String) {
    Espresso.onView(
        CoreMatchers.allOf(
            CoreMatchers.instanceOf(TextView::class.java),
            ViewMatchers.withParent(ViewMatchers.withId(toolbarId))
        )
    ).check(ViewAssertions.matches(ViewMatchers.withText(text)))
}

fun assertDialogText(@StringRes stringId: Int) {
    Espresso.onView(ViewMatchers.withText(stringId))
        .inRoot(RootMatchers.isDialog())
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
}