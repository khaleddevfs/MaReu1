package com.example.mareu.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityListMeetingBinding;
import com.example.mareu.injector.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MareuApiService;

import java.util.Calendar;
import java.util.List;


public class ListMeetingActivity extends AppCompatActivity {

    private ActivityListMeetingBinding binding;
    private MareuApiService apiService;
    private List<Meeting> meetingList;
    private DatePickerDialog datePickerDialog;
    RecyclerView recyclerView;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = DI.getMareuApiService();

        initView();

        initToolBar();

        initDatePicker();

        initClickListener();

    }

    @Override
    public void onResume() {
        super.onResume();

        initList();
    }

    private void initView() {
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void initToolBar() {
        Toolbar mToolbar = binding.toolbar;
        setSupportActionBar(mToolbar);
    }

    private void initClickListener() {
        binding.appbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        binding.meetingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(String.valueOf(meetingList), "View Binding press");
            }
        });

        binding.addMeetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
    }

    private void initList() {
        meetingList = apiService.getMeetings();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.meetingList.setLayoutManager(layoutManager);
        binding.meetingList.setAdapter(new MeetingRecyclerViewAdapter(meetingList));
    }

    private void openActivity() {
        Intent intent = new Intent(this, AddMeetingActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_par_date:
                datePickerDialog.show();
                return true;

            case R.id.filter_par_room:
            case R.id.filter:
                return true;


            case R.id.no_filter:
                initList();
                return true;

            default:
                initFilteredListByRoom(item.getTitle().toString());
                return true;



        }

    }


    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                initFilteredListByDate(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        return "JAN";
    }

    private void initFilteredListByDate(String date) {
        meetingList = apiService.getMeetingDateFilter(date);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.meetingList.setLayoutManager(layoutManager);
        binding.meetingList.setAdapter(new MeetingRecyclerViewAdapter(meetingList));
    }


    private void initFilteredListByRoom(String room) {
        Log.d("List", "initFilteredListByRoom: " + room);
        meetingList = apiService.getMeetingRoomFilter(room);
        Log.d("List", "initFilteredListByRoom: " + meetingList.size());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.meetingList.setLayoutManager(layoutManager);
        binding.meetingList.setAdapter(new MeetingRecyclerViewAdapter(meetingList));
    }


}
