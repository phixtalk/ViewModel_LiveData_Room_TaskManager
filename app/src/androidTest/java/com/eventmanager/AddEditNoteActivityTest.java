package com.eventmanager;

import android.content.Context;
import android.view.View;
import android.widget.NumberPicker;

import com.eventmanager.view.AddEditNoteActivity;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.databinding.adapters.NumberPickerBindingAdapter.setValue;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AddEditNoteActivityTest {

    @Rule
    public ActivityTestRule<AddEditNoteActivity> activityRule =
            new ActivityTestRule<>(AddEditNoteActivity.class);


    @Test
    public void userCanFillNewNoteForm() {
        onView(withId(R.id.edit_text_title)).perform(typeText("Title"));
        onView(withId(R.id.edit_text_description)).perform(typeText("Description"));

        onView(withId(R.id.number_picker_priority)).perform(new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(NumberPicker.class);
            }

            @Override
            public String getDescription() {
                return "Set the value of a NumberPicker";
            }
            @Override
            public void perform(UiController uiController, View view) {
                ((NumberPicker)view).setValue(7);
            }
        });
    }

}
