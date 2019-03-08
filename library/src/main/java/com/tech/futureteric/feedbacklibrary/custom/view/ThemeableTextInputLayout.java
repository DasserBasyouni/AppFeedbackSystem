package com.tech.futureteric.feedbacklibrary.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;

import com.google.android.material.textfield.TextInputLayout;

public class ThemeableTextInputLayout extends TextInputLayout {

    private static int mTheme;

    public static void setTheme(int theme){
        mTheme = theme;
    }

    public ThemeableTextInputLayout(Context context) {
        super(new ContextThemeWrapper(context, mTheme));
    }

    public ThemeableTextInputLayout(Context context, AttributeSet attrs) {
        super(new ContextThemeWrapper(context, mTheme), attrs);
    }

    public ThemeableTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(new ContextThemeWrapper(context, mTheme), attrs, defStyleAttr);
    }
}