package com.example.mycity

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class CityUiTest {
    @get:Rule val rule = createAndroidComposeRule<MainActivity>()
    @Test fun categories_details_back_and_recreation() {
        rule.onNodeWithTag("category_1").performClick()
        rule.onNodeWithTag("place_park_wonders").performClick()
        rule.onNodeWithTag("detail_title").assertTextEquals("Парк Чудес")
        rule.activityRule.scenario.recreate()
        rule.onNodeWithTag("detail_title").assertTextEquals("Парк Чудес")
        if (rule.activity.resources.configuration.screenWidthDp < 840) {
            rule.onNodeWithText("Назад").performClick()
        } else {
            rule.onNodeWithText("Назад").assertDoesNotExist()
        }
        rule.onNodeWithTag("place_park_wonders").assertExists()
        rule.onNodeWithTag("category_2").performClick()
        rule.onNodeWithTag("place_portcoffee").assertExists()
        rule.onNodeWithTag("category_3").performClick()
        rule.onNodeWithTag("place_kosmos").assertExists()
        rule.onNodeWithTag("category_4").performClick()
        rule.onNodeWithTag("place_promenad2").assertExists()
    }
}
