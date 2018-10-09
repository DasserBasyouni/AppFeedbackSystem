package com.tech.futureteric.feedbacklibrary.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.tech.futureteric.feedbacklibrary.R;

// TODO test when using styleable other than FeedbackStyleable
public class ThemeUtils {

    public static int getPrimaryColor(Context context, String key, int defaultTheme, Bundle bundle) {
        int themeId;

        if (bundle.containsKey(key))
            themeId = bundle.getInt(key);
        else
            themeId = defaultTheme;

        TypedArray ta = context.obtainStyledAttributes(themeId, R.styleable.FeedbackStyleable);
        int colorPrimary = ta.getInt(R.styleable.FeedbackStyleable_colorPrimary, 0);

        ta.recycle();
        return colorPrimary;
    }

    public static int getPrimaryDarkColor(Context context, String key, int defaultTheme, Bundle bundle) {
        int themeId;

        if (bundle.containsKey(key))
            themeId = bundle.getInt(key);
        else
            themeId = defaultTheme;

        TypedArray ta = context.obtainStyledAttributes(themeId, R.styleable.FeedbackStyleable);
        int colorPrimary = ta.getInt(R.styleable.FeedbackStyleable_colorPrimaryDark, 0);

        ta.recycle();
        return colorPrimary;
    }

    public static int getAccentColor(Context context, String key, int defaultTheme, Bundle bundle) {
        int themeId;

        if (bundle.containsKey(key))
            themeId = bundle.getInt(key);
        else
            themeId = defaultTheme;

        TypedArray ta = context.obtainStyledAttributes(themeId, R.styleable.FeedbackStyleable);
        int colorPrimary = ta.getInt(R.styleable.FeedbackStyleable_colorAccent, 0);

        ta.recycle();
        return colorPrimary;
    }
}
