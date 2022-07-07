package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public interface MareuApiService {


        List<Meeting> getMeetings();

       List<Meeting> getMeetingDateFilter(String date);

        List<Meeting> getMeetingRoomFilter(String roomList);

        void addMeeting(Meeting meeting);

        void deleteMeeting(Meeting meeting);
    }

