package com.example.goodluck.utils;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateTimeUtils {
    public static final ThreadLocal<SimpleDateFormat> Y_M_D_H_M_S_S = new ThreadLocal<SimpleDateFormat>() {
        @Nullable
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SS", Locale.CHINA);
        }
    };
    public static final ThreadLocal<SimpleDateFormat> yyyy_MM_dd = new ThreadLocal<SimpleDateFormat>() {
        @Nullable
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }
    };
}
