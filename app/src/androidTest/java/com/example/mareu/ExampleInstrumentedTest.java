package com.example.mareu;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.injector.DI;
import com.example.mareu.service.MareuApiService;
import com.example.mareu.ui.ListMeetingActivity;
import com.example.mareu.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private int ITEMS_COUNT;

    @Rule
    public ActivityTestRule mActivityRule =
            new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        MareuApiService apiService = DI.getMareuApiService();
        ITEMS_COUNT = apiService.getMeetings().size();
    }


    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        onView(withId(R.id.add_meetFab)).perform(click());
        // Meeting name
        onView(withId(R.id.name_input)).perform(ViewActions.typeText("Reunion 1"));
        closeSoftKeyboard();
        //Meeting Members
        onView(withId(R.id.members_input)).perform(ViewActions.typeText("test@test.com"));
        closeSoftKeyboard();



        //Meeting Date
        onView(withId(R.id.datePickerButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2022,06,10));
        onView(withText("OK")).perform(click());

        //Meeting Time
        onView(withId(R.id.timeButton)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10,25));
        onView(withText("OK")).perform(click());

        //Meeting color
        onView(withId(R.id.color_picker)).perform(ViewActions.click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.create))
                .perform(scrollTo())
                .perform(click());

        //Meeting is display
        onView(withId(R.id.meeting_list)).check(withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void myMeetingList_DeleteAction () {
        //Click on the delete button
        onView(withId(R.id.meeting_list) )
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction() ) );

        //Display the list without this meeting
        onView(withId(R.id.meeting_list) ).check(withItemCount(5) );

    }

    @Test
    public void myMeetingList_FilterAction_Date() {
        //Click on the filter button
        onView(withId(R.id.filter) ).perform(click());
        //Choose filter by date
        onView(withText("Filtrer par Date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName() ) ) ).
                perform(PickerActions.setDate(2022, 07, 10));
        onView(withText("OK")).perform(click()) ;

        //Displays the filtered meeting
        onView(withId(R.id.meeting_list) ).check(withItemCount(0) );
    }


    @Test
    public void myMeetingList_filterAction_room () {
        //Click on the filter button
        onView(withId(R.id.filter) ).perform(click() );

        //Choose filter by room
        onView(withText("Filtrer par lieu") ).perform(click() );
        onView(withText("Mario") ).perform(click() );

        //Displays the filtered meeting
        onView(withId(R.id.meeting_list) ).check(withItemCount(1) );
    }

    @Test
    public void myMeetingList_filterAction_noFilter() {
        //Click on the filter button
        onView(withId(R.id.filter) ).perform(click() );
        //No filter selected
        onView(withText("Sans Filtre") ).perform(click() );

        //Displays the list meeting without filter
        onView(withId(R.id.meeting_list) ).check(withItemCount(6) );
    }


}