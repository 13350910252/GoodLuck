package com.example.goodluck.customview.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.goodluck.R;
import com.example.goodluck.utils.FitScreenUtil;
import com.example.goodluck.utils.StatusBarUtil;

import org.xutils.x;

import java.util.Objects;

public class BaseDialogFragment extends DialogFragment {
    private boolean isInjected = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isInjected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isInjected) {
            x.view().inject(this, getView());
        }
        FitScreenUtil.setCustomDensity(getActivity(), Objects.requireNonNull(getActivity()).getApplication());
        StatusBarUtil.setTranslucentStatus(getActivity());
    }

    /**
     * 设置占位View的高度，主要是用于浸入式状态栏
     *
     * @param height 状态栏高度
     */
    protected void setActionBarHeight(View parent, int height) {
        View view = parent.findViewById(R.id.view_status);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
    }
}
