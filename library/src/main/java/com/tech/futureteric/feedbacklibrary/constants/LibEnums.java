package com.tech.futureteric.feedbacklibrary.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LibEnums {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({EMAIL, TEXT})
    public @interface InputType {}
    public static final int EMAIL = 0;
    public static final int TEXT = 1;

}
