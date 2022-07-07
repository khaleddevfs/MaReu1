package com.example.mareu;

import com.example.mareu.injector.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MareuApiService;

import org.junit.Before;
import org.junit.Test;


import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private MareuApiService apiService;

    @Before
    public void setup() { apiService = DI.getMareuApiService();}


    @Test
    public void getMeetingListWithSuccess() {
        //ARRANGE
        int listSizeExpected = 6;
        //ACT

        //ASSERT
        assertEquals(listSizeExpected, apiService.getMeetings().size());

    }

    @Test
    public void dateFilteredWithSuccess(){
        //ARRANGE
        String date = "14/07/2022";
        //ASSERT
        List<Meeting> expectedMeetingFiltered = apiService.getMeetingDateFilter(date);
        //ACT
        assertEquals(0, expectedMeetingFiltered.size());
    }

    @Test
    public void roomFilteredWithSuccess(){
        //ARRANGE
        String roomList = "J";
        //ASSERT
        List<Meeting> expectedMeetingFiltered = apiService.getMeetingRoomFilter(roomList);
        //ACT
        assertEquals(0, expectedMeetingFiltered.size());
    }

    @Test
    public void addMeetingWithSuccess() {
        //ARRANGE
        Meeting meetingToAdd = new Meeting(-12345678, "17", "15", "C", "réunion ajoutée","un@lamzone.com, deux@lamzone.com, trois@lamzone.com, quatre@lamzone.com,");
        int listSizeExcpected = 6;
        //ACT
        apiService.addMeeting(meetingToAdd);
        assertTrue(apiService.getMeetings().contains(meetingToAdd));

    }

    @Test
    public void deleteMeetingWithSuccess() {
        //ARRANGE
        Meeting meetingToDelete = apiService.getMeetings().get(0);
        //ACT
        apiService.deleteMeeting(meetingToDelete);
        //ASSERT
        assertFalse(apiService.getMeetings().contains(meetingToDelete));
    }





}