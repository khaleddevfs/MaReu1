package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MareuApiServiceGenerator {

    public static List<Meeting> Meetings = Arrays.asList(


            new Meeting( -16776961 , "Réunion A", "14h00", "Peach", "MAY 18 2022", "maxime@lamzone.com, alex@lamzone.com, damien@lamzone.com," ),

            new Meeting(-16711681, "Réunion B", "16h00", "Mario", "JUN 10 2022", "chris@lamzone.com, nathalie@lamzone.com, jonas@lamzone.com, lea@lamzone.com,"),

            new Meeting(-12303292, "Réunion C", "18h00", "Luigi ", "JUN 16 2022", "luis@lamzone.com, mathilde@lamzone.com, seb@lamzone.com,"),

            new Meeting(-7829368, "Réunion D", "19h00", "Jimmy", "JUL 05 2022", "laurette@lamzone.com, alain@lamzone.com, julie@lamzone.com, ambre@lamzone.com," ),

            new Meeting(-16711936, "Réunion E", "11h00", "Paul", "JUL 15 2022", "cedric@lamzone.com, math@lamzone.com, camille@lamzone.com,"),

            new Meeting(-65281, "Réunion F", "15h00", "Math", "SEP 10 2022", "fabien@lamzone.com, evan@lamzone.com, herve@lamzone.com,")

    );


    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(Meetings);
    }
}
