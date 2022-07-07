package com.example.mareu.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityAddMeetingBinding;
import com.example.mareu.injector.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.RoomDetails;
import com.example.mareu.service.MareuApiService;

import java.util.Calendar;
import java.util.Locale;

import yuku.ambilwarna.AmbilWarnaDialog;


public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private int hour, minute;
    private DatePickerDialog datePickerDialog;
    private MareuApiService apiService;
    private int defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = DI.getMareuApiService();

        initView();

        initDatePicker();

        initRooms();

        initClickListener();

        initSpinner();
    }

    private void initView() {
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                binding.datePickerButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private void initRooms() {
        RoomAdapter roomAdapter = new RoomAdapter(AddMeetingActivity.this, RoomDetails.getRoomList());
        binding.roomSppiner.setAdapter(roomAdapter);
    }

    private void initClickListener() {
        binding.datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                datePickerDialog.show();
            }
        });

        binding.colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });

        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeeting();
            }
        });

        binding.timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popTimePicker(view);
            }
        });
    }

    private void initSpinner() {
        binding.roomSppiner.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            @Override
            public void onPopupWindowOpened(Spinner spinner) {
                binding.roomSppiner.setBackground(getResources().getDrawable(R.drawable.backround_room_spinner_up));
            }

            @Override
            public void onPopupWindowClosed(Spinner spinner) {
                binding.roomSppiner.setBackground(getResources().getDrawable(R.drawable.backround_room_spinner));
            }
        });
        binding.roomSppiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Salle Selectionnée  " +
                        parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                binding.colorPicker.setBackgroundColor(defaultColor);
            }
        });
        colorPicker.show();
    }

    private void addMeeting() {
        Log.d("tagii", "salle: " + binding.roomSppiner.getSelectedItem().toString());
        Log.d("tagii", "getSelectedItemPosition: " + binding.roomSppiner.getSelectedItemPosition());
        Log.d("tagii", "getSelectedItemId: " + binding.roomSppiner.getSelectedItemId());
        Meeting meeting = new Meeting(
                defaultColor,
                binding.nameInput.getText().toString(),
                binding.timeButton.getText().toString(),
                binding.roomSppiner.getSelectedItem().toString(),
                binding.datePickerButton.getText().toString(),
                binding.membersInput.getText().toString());
        apiService.addMeeting(meeting);
        finish();
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

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                hour = selectedHour;
                minute = selectedMinute;
                binding.timeButton.setText(String.format(Locale.getDefault(), "%02dh%02d", hour, minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Déffinissez l'heure");
        timePickerDialog.show();
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

}
