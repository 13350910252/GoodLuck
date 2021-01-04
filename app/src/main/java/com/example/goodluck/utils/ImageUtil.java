package com.example.goodluck.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yinp.
 * @title
 * @description 将旋转的图片位置修正
 * 拍照使用的是io输入流，相册使用的是图片的真实路径
 * @date 2019/7/8,16:40.
 */

public class ImageUtil {

    //拍照
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Bitmap returnRotePhoto(int angle, InputStream is) {

        Bitmap bitmap = BitmapFactory.decodeStream(is);

        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修复图片被旋转的角度
        Bitmap bitmap1 = rotaingImageView(angle, bitmap);

        return bitmap1;
    }

    //拍照
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int readPictureDegree(InputStream istream) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(istream);//InputStream需要api大于等于24
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
            istream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    //照片
    public static Bitmap returnRotePhoto(String path) {
        //取得图片的旋转角度
        int angle = readPictureDegree(path);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        //把原图片压缩后得到Bitmap对象
        //修复图片被旋转的角度
        Bitmap bitmap1 = rotaingImageView(angle, bitmap);
        return bitmap1;
    }

    /**
     * @param path 照片路径
     * @description 取得图片的旋转角度
     * @author yinpeng
     * @date 2019/7/8,16:51
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        Bitmap returnBitmap = null;
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        returnBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (returnBitmap == null) {
            returnBitmap = bitmap;
        }
        if (bitmap != returnBitmap) {
            bitmap.recycle();
        }
        return returnBitmap;
    }
}
