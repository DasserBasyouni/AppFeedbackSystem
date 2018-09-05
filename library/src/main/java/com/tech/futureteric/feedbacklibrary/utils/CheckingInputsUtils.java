package com.tech.futureteric.feedbacklibrary.utils;

import android.os.Bundle;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_SECTIONS;

public class CheckingInputsUtils {

    public static void checkFeedbackSystemActivityBundle(Bundle bundle){
        if (bundle == null)
            throw new NullPointerException("FeedbackSystemActivity Received Bundle is null: Check your FeedbackSystemBuilder input parameters");

        if (!bundle.containsKey(BUNDLE_SECTIONS))
            throw new NullPointerException("FeedbackSystemBuilder.withSections() is required");
    }


}
