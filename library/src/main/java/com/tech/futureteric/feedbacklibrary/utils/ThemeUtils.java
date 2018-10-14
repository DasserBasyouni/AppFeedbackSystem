package com.tech.futureteric.feedbacklibrary.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.tech.futureteric.feedbacklibrary.R;

// TODO test when using styleable other than FeedbackThemeStyleable
public class ThemeUtils {

    public static int getPrimaryColor(Context context, String key, int defaultTheme, Bundle bundle) {
        int themeId;

        if (bundle.containsKey(key))
            themeId = bundle.getInt(key);
        else
            themeId = defaultTheme;

        TypedArray ta = context.obtainStyledAttributes(themeId, R.styleable.FeedbackThemeStyleable);
        int colorPrimary = ta.getInt(R.styleable.FeedbackThemeStyleable_colorPrimary, 0);

        ta.recycle();
        return colorPrimary;
    }

    public static int getPrimaryDarkColor(Context context, String key, int defaultTheme, Bundle bundle) {
        int themeId;

        if (bundle.containsKey(key))
            themeId = bundle.getInt(key);
        else
            themeId = defaultTheme;

        TypedArray ta = context.obtainStyledAttributes(themeId, R.styleable.FeedbackThemeStyleable);
        int colorPrimary = ta.getInt(R.styleable.FeedbackThemeStyleable_colorPrimaryDark, 0);

        ta.recycle();
        return colorPrimary;
    }

    public static int getAccentColor(Context context, String key, int defaultTheme, Bundle bundle) {
        int themeId;

        if (bundle.containsKey(key))
            themeId = bundle.getInt(key);
        else
            themeId = defaultTheme;

        TypedArray ta = context.obtainStyledAttributes(themeId, R.styleable.FeedbackThemeStyleable);
        int colorPrimary = ta.getInt(R.styleable.FeedbackThemeStyleable_colorAccent, 0);

        ta.recycle();
        return colorPrimary;
    }

    public static int getSpinnerTextColor(Context context, String key, int defaultTheme, Bundle bundle) {
        int themeId;

        if (bundle.containsKey(key))
            themeId = bundle.getInt(key);
        else
            themeId = defaultTheme;

        TypedArray ta = context.obtainStyledAttributes(themeId, R.styleable.FeedbackThemeStyleable);
        int spinnerStyleResource = ta.getResourceId(R.styleable.FeedbackThemeStyleable_spinnerStyle, 0);

        ta = context.obtainStyledAttributes(spinnerStyleResource, R.styleable.FeedbackSpinnerStyleable);
        int spinnerTextColorResource = ta.getResourceId(R.styleable.FeedbackSpinnerStyleable_android_textColor, 0);

        ta.recycle();
        return context.getResources().getColor(spinnerTextColorResource);
    }
}