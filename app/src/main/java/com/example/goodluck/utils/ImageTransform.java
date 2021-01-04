package com.example.goodluck.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * 对图片进行转换，如转换为string
 */
public class ImageTransform {
    /**
     * png格式改变quality是没有作用的
     * 因为png格式是无损压缩
     * @param bitmap
     * @return
     */
    public static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        String base64String = Base64.encodeToString(bytes, 0, bytes.length, Base64.NO_WRAP);
        if (bytes != null) {
            bytes = null;
        }
        if (stream != null) {
            stream = null;
        }
        return base64String;
    }

    /**
     * 可以改变压缩质量
     * @param bitmap
     * @param quality
     * @return
     */
    public static String bitmapToString(Bitmap bitmap,int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        byte[] bytes = stream.toByteArray();
        String base64String = Base64.encodeToString(bytes, 0, bytes.length, Base64.NO_WRAP);
        if (bytes != null) {
            bytes = null;
        }
        if (stream != null) {
            stream = null;
        }
        return base64String;
    }
}
