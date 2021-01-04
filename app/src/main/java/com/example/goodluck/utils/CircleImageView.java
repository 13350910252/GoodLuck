package com.example.goodluck.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author yinp.
 * @title
 * @description 将图片圆形显示，src设置的图片
 * @date 2019/7/5,15:37.
 */

public class CircleImageView extends AppCompatImageView {

    private float radius;
    private float mScale;
    private Paint paint;
    private Matrix matrix;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        matrix = new Matrix();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int size = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2;
        double a = getMeasuredHeight() * getMeasuredHeight() + getMeasuredWidth() * getMeasuredWidth();
        double b = Math.sqrt(a);
        int size = (int) b;
        radius = size / 2;
        setMeasuredDimension(size, size);
    }

    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mScale = (radius * 2.0f) / Math.min(bitmap.getWidth(), bitmap.getHeight());

            matrix.setScale(mScale, mScale);
            bitmapShader.setLocalMatrix(matrix);
            paint.setShader(bitmapShader);
            canvas.drawCircle(radius, radius, radius, paint);
        } else {
            super.onDraw(canvas);
        }

    }

}
