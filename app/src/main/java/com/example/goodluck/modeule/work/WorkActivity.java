package com.example.goodluck.modeule.work;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.goodluck.R;
import com.example.goodluck.base.activity.AppBaseFragmentActivity;
import com.example.goodluck.databinding.ActivityWorkBinding;
import com.example.goodluck.modeule.home.fragment.HomeFragment;
import com.example.goodluck.modeule.me.fragment.MeFragment;
import com.example.goodluck.modeule.recreation.fragment.RecreationFragment;
import com.example.goodluck.modeule.study.fragment.StudyFragment;
import com.example.goodluck.modeule.tools.fragment.ToolsFragment;
import com.example.goodluck.utils.StatusBarUtil;
import com.example.goodluck.utils.permission.PermissionListener;
import com.example.goodluck.utils.permission.PermissionsUtil;

public class WorkActivity extends AppBaseFragmentActivity<ActivityWorkBinding> {

    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private Fragment currFragment;

    public HomeFragment homeFragment;
    public StudyFragment studyFragment;
    public ToolsFragment toolsFragment;
    public RecreationFragment recreationFragment;
    public MeFragment meFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        bindData();
    }

    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        fragmentManager = getSupportFragmentManager();

        binding.headerLayout.headerBackImg.setVisibility(View.GONE);
        binding.headerLayout.headerCenterTitle.setText("首页");
        binding.rgBottomGroup.setOnCheckedChangeListener(new CheckedListener());
    }

    @Override
    public void bindData() {
        super.bindData();
        changeFragment(R.id.rb_home_page);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PermissionsUtil.TipInfo tipWriteExternalStorage = new PermissionsUtil.TipInfo(getString(R.string.remind_text), getString(R.string.gps_permission_open), "取消", "打开权限");
        PermissionsUtil.requestPermission(getApplication(), new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
            }
        }, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, true, tipWriteExternalStorage);
    }

    private class CheckedListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_home_page) {
                changeFragment(R.id.rb_home_page);
            } else if (checkedId == R.id.rb_study_page) {
                changeFragment(R.id.rb_study_page);
            } else if (checkedId == R.id.rb_tools_page) {
                changeFragment(R.id.rb_tools_page);
            } else if (checkedId == R.id.rb_recreation_page) {
                changeFragment(R.id.rb_recreation_page);
            } else if (checkedId == R.id.rb_me_page) {
                changeFragment(R.id.rb_me_page);
            }
        }
    }

    private void changeFragment(int checkedId) {
        transaction = fragmentManager.beginTransaction();
        if (checkedId == R.id.rb_home_page) {
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
                transaction.add(R.id.fl_page, homeFragment);
            }
            if (currFragment != null) {
                transaction.hide(currFragment);
            }
            currFragment = homeFragment;
            transaction.show(currFragment);
            transaction.commit();
        } else if (checkedId == R.id.rb_study_page) {
            if (studyFragment == null) {
                studyFragment = new StudyFragment();
                transaction.add(R.id.fl_page, studyFragment);
            }
            if (currFragment != null) {
                transaction.hide(currFragment);
            }
            currFragment = studyFragment;
            transaction.show(currFragment);
            transaction.commit();
        } else if (checkedId == R.id.rb_tools_page) {
            if (toolsFragment == null) {
                toolsFragment = new ToolsFragment();
                transaction.add(R.id.fl_page, toolsFragment);
            }
            if (currFragment != null) {
                transaction.hide(currFragment);
            }
            currFragment = toolsFragment;
            transaction.show(currFragment);
            transaction.commit();
        } else if (checkedId == R.id.rb_recreation_page) {

            if (recreationFragment == null) {
                recreationFragment = new RecreationFragment();
                transaction.add(R.id.fl_page, recreationFragment);
            }
            if (currFragment != null) {
                transaction.hide(currFragment);
            }
            currFragment = recreationFragment;
            transaction.show(currFragment);
            transaction.commit();
        } else if (checkedId == R.id.rb_me_page) {
            if (meFragment == null) {
                meFragment = new MeFragment();
                transaction.add(R.id.fl_page, meFragment);
            }
            if (currFragment != null) {
                transaction.hide(currFragment);
            }
            currFragment = meFragment;
            transaction.show(currFragment);
            transaction.commit();
        }
    }
}
