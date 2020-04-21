package com.lomza.uitestsapp.tests

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not

fun assertToolbarTitle(@IdRes toolbarId: Int,  text: String) {
    onView(
        allOf(
            instanceOf(TextView::class.java),
            withParent(withId(toolbarId))
        )
    ).check(matches(withText(text)))
}

fun assertDialogText(@StringRes stringId: Int) {
    onView(withText(stringId)).inRoot(isDialog()).check(matches(isDisplayed()))
}

fun assertToastWithText(text: String, view: View) {
    onView(withText(text))
        .inRoot(withDecorView(not(`is`(view))))
        .check(matches(isDisplayed()))
}