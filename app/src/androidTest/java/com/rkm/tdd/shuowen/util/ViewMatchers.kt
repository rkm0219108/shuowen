package com.rkm.tdd.shuowen.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Description
import org.hamcrest.Matcher

object ViewMatchers {

    fun withTextOnItemAtPosition(position: Int, targetId: Int, text: String): Matcher<in View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $targetId at position $position")
            }

            public override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position) ?: return false
                val targetView = viewHolder.itemView.findViewById<View>(targetId)
                return withText(text).matches(targetView)
            }
        }
    }
}
