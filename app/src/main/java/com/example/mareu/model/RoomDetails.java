package com.example.mareu.model;

import com.example.mareu.R;

import java.util.ArrayList;
import java.util.List;

public class RoomDetails {

    public static List<Room> getRoomList() {
        List<Room> roomList = new ArrayList<>();

        Room Peach = new Room();
        Peach.setName("Peach");
        Peach.setImage(R.drawable.meeting_icon);
        roomList.add(Peach);

        Room Mario = new Room();
        Mario.setName("Mario");
        Mario.setImage(R.drawable.meeting_icon);
        roomList.add(Mario);

        Room Luigi = new Room();
        Luigi.setName("Luigi");
        Luigi.setImage(R.drawable.meeting_icon);
        roomList.add(Luigi);

        Room Jimmy = new Room();
        Jimmy.setName("Jimmy");
        Jimmy.setImage(R.drawable.meeting_icon);
        roomList.add(Jimmy);

        Room Paul = new Room();
        Paul.setName("Paul");
        Paul.setImage(R.drawable.meeting_icon);
        roomList.add(Paul);

        Room Mathey = new Room();
        Mathey.setName("Mathey");
        Mathey.setImage(R.drawable.meeting_icon);
        roomList.add(Mathey);

        Room Pavillon = new Room();
        Pavillon.setName("Pavillon");
        Pavillon.setImage(R.drawable.meeting_icon);
        roomList.add(Pavillon);

        return roomList;
    }

}

