package com.example.goodluck.modeule.tools.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.goodluck.base.fragment.AppBaseFragment;
import com.example.goodluck.databinding.FragmentToolsPageBinding;
import com.example.goodluck.modeule.tools.task.activity.DayTaskActivity;
import com.example.goodluck.utils.FileUtils;

public class ToolsFragment extends AppBaseFragment<FragmentToolsPageBinding> {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    protected void initViews(View view) {
        binding.btnDayTask.setOnClickListener(this);
        binding.btnCopyDatabases.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == binding.btnDayTask) {
            startActivity(new Intent(getContext(), DayTaskActivity.class));
        } else if (v == binding.btnCopyDatabases) {
            FileUtils.copyDataBase();
        }
    }
}
