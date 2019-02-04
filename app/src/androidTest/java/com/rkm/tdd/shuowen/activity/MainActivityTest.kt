package com.rkm.tdd.shuowen.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.rkm.tdd.shuowen.R
import com.rkm.tdd.shuowen.util.ViewMatchers.withTextOnItemAtPosition
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun wordListShowing() {
        onView(withId(R.id.word_list)).check(matches(isDisplayed()))

        onView(withId(R.id.word_list)).check(matches(withTextOnItemAtPosition(0, R.id.tv_name, "一")))
    }
}
