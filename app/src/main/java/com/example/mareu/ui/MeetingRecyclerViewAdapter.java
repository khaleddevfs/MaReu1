package com.example.mareu.ui;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.ListItemsBinding;
import com.example.mareu.model.Meeting;

import java.util.List;


public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder>{

    private final List<Meeting> meetings;

    public MeetingRecyclerViewAdapter(List<Meeting> mMeeting) {
        this.meetings = mMeeting;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ListItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Meeting meeting = meetings.get(position);
        Drawable background = holder.binding.colorCircle.getBackground();
        background.setTint(meeting.getColor());
        Log.d("tagii", " ada getPlace: "+ meeting.getPlace());
        holder.binding.meetingDescription.setText(meeting.getName() + " - " + meeting.getTime() + " - " + meeting.getPlace());
        holder.binding.meetingMembers.setText(meeting.getMembers());
        holder.binding.deleteMeetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetings.remove(meeting);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ListItemsBinding binding;

        public ViewHolder(ListItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}