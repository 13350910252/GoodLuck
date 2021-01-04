package com.example.goodluck.customview.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.goodluck.R;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.dialog_common_tip)
public class CommonTipDFDialog extends BaseDialogFragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.getDecorView().setBackgroundResource(R.drawable.shape_white_radius8_tl_tr_bg);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.windowAnimations = R.style.ItemDialog;
            window.setAttributes(layoutParams);
        }
    }

    //标题
    private String title;
    //内容
    private String content;
    //左边按钮文字
    private String leftButton;
    //右边文字
    private String rightButton;
    //是否能够点击外边取消
    private boolean isCancel;

    private CommonTipDFDialog(Builder builder) {
        title = builder.title;
        content = builder.content;
        leftButton = builder.leftButton;
        rightButton = builder.rightButton;
        isCancel = builder.isCancel;
    }

    public static final class Builder {
        private String title;
        private String content;
        private String leftButton;
        private String rightButton;
        private boolean isCancel;
        CommonTipDFDialog dfDialog;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder leftButton(String leftButton) {
            this.leftButton = leftButton;
            return this;
        }

        public Builder rightButton(String rightButton) {
            this.rightButton = rightButton;
            return this;
        }

        public Builder isCancel(boolean isCancel) {
            this.isCancel = isCancel;
            return this;
        }

        public CommonTipDFDialog create() {
            dfDialog = new CommonTipDFDialog(this);
            return dfDialog;
        }

        public void show(FragmentManager fragmentManager, String tag) {
            if (dfDialog != null) {
                dfDialog.show(fragmentManager, tag);
            }
        }

        public void show(FragmentTransaction transaction, String tag) {
            if (dfDialog != null) {
                dfDialog.show(transaction, tag);
            }
        }
    }
}
