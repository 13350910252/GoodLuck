package com.example.goodluck.customview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.goodluck.R;
import com.example.goodluck.utils.AppUtils;

public class CustomTextView extends AppCompatTextView {
    Context context;

    public CustomTextView(@NonNull Context context) {
        this(context, null);
    }

    public CustomTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private float radius;
    private float lt_radius;
    private float rt_radius;
    private float lb_radius;
    private float rb_radius;
    // 外部矩形弧度,直接所有圆角一致
    float[] allRadius;
    // 外部矩形弧度,控制每个角
    float[] exquisiteRadius;
    Drawable normal_background = null;
    Drawable pressed_background = null;
    private int state_pressed = android.R.attr.state_pressed;
    private int[] states = new int[10];
    private int[] _states = new int[10];
    private StateListDrawable stateListDrawable;

    private void init(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        normal_background = ta.getDrawable(R.styleable.CustomTextView_normal_background);
        pressed_background = ta.getDrawable(R.styleable.CustomTextView_pressed_background);
        radius = ta.getInteger(R.styleable.CustomTextView_radius, 0);
        lt_radius = ta.getInteger(R.styleable.CustomTextView_lt_radius, 0);
        rt_radius = ta.getInteger(R.styleable.CustomTextView_rt_radius, 0);
        lb_radius = ta.getInteger(R.styleable.CustomTextView_lb_radius, 0);
        rb_radius = ta.getInteger(R.styleable.CustomTextView_lb_radius, 0);
        ta.recycle();
        setOnClickListener(v -> {
            //必须加上这一句，否者点击没效果
        });
        states[0] = state_pressed;
        _states[0] = -state_pressed;
        if (normal_background instanceof ColorDrawable && pressed_background instanceof ColorDrawable) {
            stateListDrawable = new StateListDrawable();
            setRadius();
            if (shape == null) {
                setSelectedColor((ColorDrawable) pressed_background, (ColorDrawable) normal_background, states, _states);
            } else {
                setSelectedShapeColor((ColorDrawable) pressed_background, states);
                setSelectedShapeColor((ColorDrawable) normal_background, _states);
                setBackground(stateListDrawable);
            }

        } else if (normal_background instanceof BitmapDrawable && pressed_background instanceof BitmapDrawable) {
            stateListDrawable = new StateListDrawable();
            setSelectedShapeColor((BitmapDrawable) pressed_background, states);
            setSelectedShapeColor((BitmapDrawable) normal_background, _states);
            setBackground(stateListDrawable);
        } else if (normal_background instanceof ColorDrawable && pressed_background instanceof BitmapDrawable) {
            setBackground(pressed_background);
        } else if (normal_background instanceof BitmapDrawable && pressed_background instanceof ColorDrawable) {
            setBackground(normal_background);
        } else if (normal_background != null && pressed_background == null) {
            setBackground(normal_background);
        } else if (normal_background == null && pressed_background != null) {
            setBackground(pressed_background);
        } else {
            setBackground(null);
        }

    }

    private RoundRectShape shape = null;

    /**
     * 设置圆角shape
     */
    private void setRadius() {
        if (radius != 0) {
            radius = AppUtils.dpToPx(context, radius);
            allRadius = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
            shape = new RoundRectShape(allRadius, null, null);
        } else if (lt_radius != 0 || rt_radius != 0 || rb_radius != 0 || lb_radius != 0) {
            lt_radius = AppUtils.dpToPx(context, lt_radius);
            rt_radius = AppUtils.dpToPx(context, rt_radius);
            lb_radius = AppUtils.dpToPx(context, lb_radius);
            rb_radius = AppUtils.dpToPx(context, rb_radius);
            exquisiteRadius = new float[]{lt_radius, lt_radius, rt_radius, rt_radius, rb_radius, rb_radius, lb_radius, lb_radius};
            shape = new RoundRectShape(exquisiteRadius, null, null);
        }
    }

    /**
     * 设置颜色随着选择变动
     *
     * @param drawable
     * @param state
     */
    private void setSelectedShapeColor(ColorDrawable drawable, int... state) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
        shapeDrawable.getPaint().setColor(drawable.getColor());
        stateListDrawable.addState(state, shapeDrawable);
    }

    private void setSelectedShapeColor(BitmapDrawable drawable, int... state) {
        stateListDrawable.addState(state, drawable);
    }

    private void setSelectedColor(ColorDrawable drawable1, ColorDrawable drawable2, int[] one, int[] two) {
        ColorStateList colorStateList = new ColorStateList(new int[][]{one, two}, new int[]{drawable1.getColor(), drawable2.getColor()});
        setBackgroundTintList(colorStateList);
    }
}
