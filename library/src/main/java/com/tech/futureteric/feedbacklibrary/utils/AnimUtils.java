package com.tech.futureteric.feedbacklibrary.utils;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

public class AnimUtils {

    public static void changeToolAndStatusBarsColorWithAnimation(int statusBarColor, int toolbarColor
            , int statusBarToColor, int toolbarToColor, AppCompatActivity activity, boolean spinnerInitialized) {

        if (spinnerInitialized){
        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(animation -> {
            // Use animation position to blend colors.
            float position = animation.getAnimatedFraction();
            int blended;

            if (statusBarColor != 0) {
                // Apply blended color to the status bar.
                blended = blendColors(statusBarColor, statusBarToColor, position);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    activity.getWindow().setStatusBarColor(blended);
            }

            // Apply blended color to the ActionBar.
            blended = blendColors(toolbarColor, toolbarToColor, position);
            ColorDrawable background = new ColorDrawable(blended);
            activity.getSupportActionBar().setBackgroundDrawable(background);
        });
        anim.setDuration(1000).start();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                activity.getWindow().setStatusBarColor(statusBarToColor);
            activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(toolbarToColor));
        }
    }

    private static int blendColors(int from, int to, float ratio) {
        final float inverseRatio = 1f - ratio;

        final float r = Color.red(to) * ratio + Color.red(from) * inverseRatio;
        final float g = Color.green(to) * ratio + Color.green(from) * inverseRatio;
        final float b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio;

        return Color.rgb((int) r, (int) g, (int) b);
    }
}
