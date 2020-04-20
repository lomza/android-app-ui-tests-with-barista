package com.lomza.uitestsapp.tests.groceries

import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.lomza.uitestsapp.GroceriesListActivity
import com.lomza.uitestsapp.GroceryDetailsActivity
import com.lomza.uitestsapp.R
import com.lomza.uitestsapp.tests.assertDialogText
import com.lomza.uitestsapp.tests.assertToolbarTitle
import com.schibsted.spain.barista.assertion.BaristaAssertions.assertThatBackButtonClosesTheApp
import com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertEnabled
import com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem
import com.schibsted.spain.barista.interaction.BaristaMenuClickInteractions.clickMenu
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.Matchers.isEmptyString
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GroceriesSteps {

    @get:Rule
    var activityRule = ActivityTestRule<GroceriesListActivity>(GroceriesListActivity::class.java, true, false)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun cleanup() {
        Intents.release()
    }

    @Given("The app started")
    fun the_app_started() {
        activityRule.launchActivity(Intent())
    }

    @When("I press back")
    fun i_press_back() {
        // intentionally do nothing, assertion is done in the next step
    }

    @Then("I should exit the app")
    fun i_should_exit_the_app() {
        assertThatBackButtonClosesTheApp()
    }

    @Then("I see Groceries list screen")
    fun i_see_groceries_list_screen() {
        intended(hasComponent(GroceriesListActivity::class.java.name))

        val toolbarTitle = getInstrumentation().targetContext.getString(R.string.groceries_list_activity)
        assertToolbarTitle(R.id.vGroceriesListToolbar, toolbarTitle)
        assertDisplayed(R.string.groceries_list_header)
        assertRecyclerViewItemCount(R.id.vGroceriesRecyclerView, expectedItemCount = 10)
    }

    @When("I click on question mark action item")
    fun i_click_on_question_mark_action_item() {
        clickOn(R.id.action_help)
    }

    @Then("I see Help web page")
    fun i_see_help_web_page() {
        val url = getInstrumentation().targetContext.getString(R.string.groceries_list_help_url)
        intended(hasData(Uri.parse(url)))
    }

    @When("I click on first list item")
    fun i_click_on_first_grocery_list_item() {
        clickListItem(R.id.vGroceriesRecyclerView, position = 0)
    }

    @When("I click on last list item")
    fun i_click_on_last_grocery_list_item() {
        clickListItem(R.id.vGroceriesRecyclerView, position = 9)
    }

    @When("I click on {string} list item")
    fun i_click_on_grocery_list_item(grocery: String) {
        clickOn(grocery)
    }

    @Then("I see {string} grocery details screen")
    fun i_see_grocery_details_screen(grocery: String) {
        intended(hasComponent(GroceryDetailsActivity::class.java.name))

        assertHasDrawable(R.id.imageViewCollapsing, R.drawable.groceries)
        assertToolbarTitle(R.id.vGroceryDetailsToolbar, grocery)
        assertDisplayed(R.id.vGroceryDetailsNoteEdit)
        val noteHint = getInstrumentation().targetContext.getString(R.string.grocery_details_note_hint)
        assertHint(R.id.vGroceryDetailsNoteEdit, noteHint)
        assertDisplayed(R.string.grocery_details_note_clear)
        assertEnabled(R.string.grocery_details_note_clear)
    }

    @And("I navigate to {string} grocery details screen")
    fun i_navigate_to_grocery_details_screen(grocery: String) {
        i_click_on_grocery_list_item(grocery)
        i_see_grocery_details_screen(grocery)
    }

    @And("I input {string} note")
    fun i_input_note(note: String) {
        writeTo(R.id.vGroceryDetailsNoteEdit, note)
    }

    @When("I press Clear")
    fun i_press_clear() {
        clickOn(R.string.grocery_details_note_clear)
    }

    @When("I see empty note")
    fun i_see_empty_note() {
        onView(withId(R.id.vGroceryDetailsNoteEdit)).check(matches(withText(isEmptyString())))
    }

    @When("I click on Delete menu item")
    fun i_click_on_delete_menu_item() {
        clickMenu(R.id.action_delete)
    }

    @And("I see delete confirmation dialog")
    fun i_see_delete_confirmation_dialog() {
        assertDialogText(R.string.grocery_details_note_delete_dialog_title)
        assertDialogText(R.string.grocery_details_note_delete_dialog_no)
        assertDialogText(R.string.grocery_details_note_delete_dialog_yes)
    }

    @And("I confirm the deletion")
    fun i_confirm_the_deletion() {
        clickOn(R.string.grocery_details_note_delete_dialog_yes)
    }
}