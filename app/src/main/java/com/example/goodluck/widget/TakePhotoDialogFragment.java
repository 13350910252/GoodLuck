package com.example.goodluck.widget;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.goodluck.R;
import com.example.goodluck.base.fragment.AppBaseDialogFragment;
import com.example.goodluck.databinding.DialogTakePhotoBinding;
import com.example.goodluck.utils.ImageUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TakePhotoDialogFragment extends AppBaseDialogFragment<DialogTakePhotoBinding> {
    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;//拍照
    private static final int CHOOSE_PHOTO = 2;//选择相片
    private static final int PERMISSION_REQUEST = 3;//获取本地信息的权限

    public static TakePhotoDialogFragment getInstance() {
        TakePhotoDialogFragment fragment = new TakePhotoDialogFragment();

        return fragment;
    }

    @Override
    protected void initViews(View view) {
        binding.tvTakePhoto.setOnClickListener(this);
        binding.tvChoosePicture.setOnClickListener(this);
        binding.tvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.tvTakePhoto) {
            takePhoto();
        } else if (view == binding.tvChoosePicture) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        , 1);
            } else {
                openAlbum();
            }
        } else if (view == binding.tvCancel) {
            dismiss();
        }
    }

    /**
     * @description 拍照
     * @author yinpeng
     * @date 2019/7/8,13:57
     */
    private void takePhoto() {
        File file = new File(getActivity().getExternalCacheDir(), "output_image.jpg");

        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(getContext(), "com.example.exclusive", file);
        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    /**
     * @description 打开相册
     * @author yinpeng
     * @date 2019/7/8,15:35
     */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getContext(), "对不起！您没有相关权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == 0) {
                    dismiss();
                    return;
                }
                try {
                    //获取图片旋转的角度
                    int degree = ImageUtil.readPictureDegree(getActivity().getContentResolver().openInputStream(imageUri));
                    Bitmap bitmap = ImageUtil.returnRotePhoto(degree, getActivity().getContentResolver().openInputStream(imageUri));
                    onCallBackBitmapListener = (OnCallBackBitmapListener) getActivity();
                    onCallBackBitmapListener.onCallBackBitmap(imageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case CHOOSE_PHOTO:
                if (data == null) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    handleImageOnKitKat(data);
                } else {
                    handleImageBeforeKitKat(data);
                }
                break;
        }
    }

    private Uri pictureUri;

    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        pictureUri = data.getData();
        if (DocumentsContract.isDocumentUri(getContext(), pictureUri)) {
            String docId = DocumentsContract.getDocumentId(pictureUri);
            if ("com.android.providers.media.documents".equals(pictureUri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(pictureUri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(pictureUri.getScheme())) {
            //如果是content类型的uri，则使用普通类型
            imagePath = getImagePath(pictureUri, null);
        } else if ("file".equalsIgnoreCase(pictureUri.getScheme())) {
            //如果是file类型的uri，则直接获取图片的路径即可
            imagePath = pictureUri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过uri和selection来获取图片的真实路径
        Cursor cursor = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA
        }, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = ImageUtil.returnRotePhoto(imagePath);
            //BitmapFactory.decodeFile(imagePath);
            onCallBackBitmapListener = (OnCallBackBitmapListener) getActivity();
            onCallBackBitmapListener.onCallBackBitmap(pictureUri);
        } else {
            Toast.makeText(getContext(), "图片资源为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window != null) {
//            window.getDecorView().setBackgroundResource(R.drawable.bg_dialog_fragment_string);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.windowAnimations = R.style.ItemDialog;
            window.setAttributes(layoutParams);
        }

    }

    public interface OnCallBackBitmapListener {
        void onCallBackBitmap(Uri uri);
    }

    private OnCallBackBitmapListener onCallBackBitmapListener;
}
