package com.example.goodluck.modeule.home.calendar.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.goodluck.R;
import com.example.goodluck.base.activity.AppBaseActivity;
import com.example.goodluck.databinding.ActivityMyCalendarBinding;
import com.example.goodluck.utils.StatusBarUtil;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

public class MyCalendarActivity extends AppBaseActivity<ActivityMyCalendarBinding> implements CalendarView.OnYearChangeListener, CalendarView.OnCalendarSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        binding.headerLayout.headerBg.setBackgroundColor(Color.TRANSPARENT);
        binding.headerLayout.headerCenterTitle.setText(R.string.rili);

        binding.tvYear.setText(binding.calendarView.getCurYear() + "-" + binding.calendarView.getCurMonth());
        binding.ctvToday.setText(String.valueOf(binding.calendarView.getCurDay()));

        binding.tvYear.setOnClickListener(this);
        binding.ctvToday.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (binding.tvYear == view) {

        } else if (view == binding.ctvToday) {
            binding.calendarView.scrollToCurrent();
        }
    }

    @Override
    public void onYearChange(int year) {

    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        int y = calendar.getYear();
        int m = calendar.getMonth();
        int d = calendar.getDay();
        binding.tvYear.setText(y + "-" + m);
    }
}