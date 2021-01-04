package com.example.goodluck.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.goodluck.R;

public class DialogUtils {
    public static View createDialog(Activity activity, String message, String cancelText, String commitText, int id) {
        View view = createDialogDelete(activity);
        setDialogDelete(view, message, cancelText, commitText, id);
        return view;
    }

    private static View createDialogDelete(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_common_tip, null);
        final AlertDialog dialog = new AlertDialog.Builder(activity).setView(view).create();
        TextView cancel_button = view.findViewById(R.id.tv_dialog_common_right);
        TextView queren_button = view.findViewById(R.id.tv_dialog_common_left);
        LinearLayout ll_ok_cancel = view.findViewById(R.id.ll_dialog_common_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        view.setTag(R.id.ll_ok_cancel_key, ll_ok_cancel);
        view.setTag(R.id.cancel_key, cancel_button);
        view.setTag(R.id.dialog_key, dialog);
        view.setTag(R.id.button_key, queren_button);
        return view;
    }

    public static void setDialogDelete(View view, String message, String cancelText, String commitText, int id) {
        AlertDialog dialog = (AlertDialog) view.getTag(R.id.dialog_key);
        TextView message_text = view.findViewById(R.id.tv_dialog_common_content);
        TextView title = view.findViewById(R.id.title);
        if (id != -1) {
            title.setBackgroundResource(id);
        }
        TextView cancel_button = view.findViewById(R.id.tv_dialog_common_right);
        TextView queren_button =  view.findViewById(R.id.tv_dialog_common_left);
        message_text.setText(message);
        cancel_button.setText(cancelText);
        queren_button.setText(commitText);
        dialog.show();

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.drawable.shape_white_radius8_bg);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) AppUtils.dpToPx(view.getContext(), 300);
        window.setGravity( Gravity.CENTER);
        window.setAttributes(params);



    }


}
