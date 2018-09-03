package com.tech.futureteric.feedbacklibrary.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LibEnums {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FREQUENTLY_ASKED_QUESTIONS, FEATURE_REQUEST, GENERAL_FEEDBACK, CONTACT_US, BUG_REPORT, ALL})
    public @interface Sections {}
    public static final int FREQUENTLY_ASKED_QUESTIONS = 0;
    public static final int FEATURE_REQUEST = 1;
    public static final int GENERAL_FEEDBACK = 2;
    public static final int CONTACT_US = 3;
    public static final int BUG_REPORT = 4;
    public static final int ALL = 5;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BUTTER_KNIFE, R_X_BINDING, AUTO_DISPOSE})
    public @interface ViewBinder {}
    public static final int BUTTER_KNIFE = 0;
    public static final int R_X_BINDING = 1;
    public static final int AUTO_DISPOSE = 2;
}
