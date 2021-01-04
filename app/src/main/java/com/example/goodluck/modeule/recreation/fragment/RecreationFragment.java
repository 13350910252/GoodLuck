package com.example.goodluck.modeule.recreation.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.goodluck.base.fragment.AppBaseFragment;
import com.example.goodluck.databinding.FragmentRecreationPageBinding;

public class RecreationFragment extends AppBaseFragment<FragmentRecreationPageBinding> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }
    @Override
    protected void initViews(View view) {

    }
}
