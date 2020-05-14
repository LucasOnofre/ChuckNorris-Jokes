package com.onoffrice.norrisJokes.categories

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.onoffrice.norrisJokes.R
import com.onoffrice.norrisJokes.ui.categories.CategoriesActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ActivityTest {

    private var server = MockWebServer()

    @get:Rule
    val activityRule = ActivityTestRule(CategoriesActivity::class.java, false, false)

    @Test
    fun test_isActivityInView() {
        launchActivity<CategoriesActivity>().apply {
            onView(withId(R.id.mainContainer)).check(ViewAssertions.matches(isDisplayed()))
            onView(withText("Categorias")).check(ViewAssertions.matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        server.start(serverPort)

        launchActivity<CategoriesActivity>().apply {

            server.enqueue(categoriesSuccessResponse)

            onView(withId(R.id.categoriesRv)).check(ViewAssertions.matches(isDisplayed()))

        }

        server.close()
    }

    companion object {
        private const val serverPort = 8080

        private val categoriesSuccessResponse by lazy {
            val body =
                "[\"animal\",\"career\",\"celebrity\",\"dev\",\"explicit\",\"fashion\",\"food\",\"history\",\"money\",\"movie\",\"music\",\"political\",\"religion\",\"science\",\"sport\",\"travel\"]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }
    }
}