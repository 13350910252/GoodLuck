package com.example.goodluck.modeule.tools.task.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.goodluck.R;
import com.example.goodluck.base.activity.AppBaseFragmentActivity;
import com.example.goodluck.databinding.ActivityAddTaskBinding;
import com.example.goodluck.modeule.tools.task.mvp.TaskCVIew;
import com.example.goodluck.modeule.tools.task.mvp.TaskPresenter;
import com.example.goodluck.utils.DialogUtils;
import com.example.goodluck.utils.StatusBarUtil;

public class AddTaskActivity extends AppBaseFragmentActivity<ActivityAddTaskBinding> implements TaskCVIew.SQLiteCallBack, TaskCVIew {
    TaskPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        bindData();
    }

    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        binding.headerLayout.headerCenterTitle.setText(R.string.add_task);
        binding.headerLayout.headerBeforeEnd.setText(R.string.save);
        binding.headerLayout.headerBeforeEnd.setOnClickListener(this);

        presenter = new TaskPresenter(this);
    }

    @Override
    protected void bindData() {
        super.bindData();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (isClick) {
            return;
        }
        isClick = true;
        new Handler().postDelayed(runnable, 500);
        if (view == binding.headerLayout.headerBeforeEnd) {

            View view1 = DialogUtils.createDialog(this, "提示", "取消", "保存", -1);
            final AlertDialog dialog = (AlertDialog) view1.getTag(R.id.dialog_key);
            TextView sure = (TextView) view1.getTag(R.id.button_key);
            sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.addSQLiteData(binding.etAddTitle.getText().toString(), binding.etAddContent.getText().toString());
                    dialog.dismiss();
                    finish();
                }
            });
        }
    }

    public void addTask() {

    }

    @Override
    public void onSqlSuccess(String result) {
        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSqlFail(String result) {
        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
    }

}
