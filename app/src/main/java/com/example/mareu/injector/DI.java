package com.example.mareu.injector;

import com.example.mareu.service.MareuApiService;
import com.example.mareu.service.MeetingApiServiceRepository;

public class DI {

    private static MareuApiService apiService = new MeetingApiServiceRepository();

    public static MareuApiService getMareuApiService() {
        return apiService;
    }
}
