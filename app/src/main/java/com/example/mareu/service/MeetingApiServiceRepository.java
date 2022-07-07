package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiServiceRepository implements MareuApiService {

    private List<Meeting> meetings = MareuApiServiceGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }


    @Override
    public List<Meeting> getMeetingDateFilter(String date) {
        List<Meeting> mMeetingFilteredDate = new ArrayList<Meeting>();
        for (Meeting meeting : meetings) {
            if (meeting.getDate().equals(date)) {
                mMeetingFilteredDate.add(meeting);
            }
        }
        return mMeetingFilteredDate;
    };

    @Override
    public List<Meeting> getMeetingRoomFilter(String room) {
        List<Meeting> mMeetingFilteredRoom = new ArrayList<Meeting>();
        for (Meeting meeting : meetings) {
            if (meeting.getPlace().equals(room))
                mMeetingFilteredRoom.add(meeting);
        }
        return mMeetingFilteredRoom;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);

    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);

    }
}
