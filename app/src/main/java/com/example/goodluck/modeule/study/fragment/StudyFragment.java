package com.example.goodluck.modeule.study.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.goodluck.base.fragment.AppBaseFragment;
import com.example.goodluck.databinding.FragmentStudyPageBinding;
import com.example.goodluck.modeule.study.activity.StudyAndroidActivity;

public class StudyFragment extends AppBaseFragment<FragmentStudyPageBinding> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }
    @Override
    protected void initViews(View view) {
        binding.btnAndroid.setOnClickListener(this);
        binding.btnJava.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == binding.btnAndroid) {
            startActivity(new Intent(getActivity(), StudyAndroidActivity.class));
        } else if (v == binding.btnJava) {

        }
    }

}
