package com.tech.futureteric.feedbacklibrary.utils;

import android.content.Context;

import com.tech.futureteric.feedbacklibrary.R;

import java.util.List;

public class UiUtils {

    public static int getSectionColorId(Context context, String sectionName, List<Integer> customColorsList, int position) {
        if (customColorsList == null) {
            if (sectionName.equals(context.getString(R.string.label_frequently_asked_questions)))
                return R.color.faqBtn_900;
            else if (sectionName.equals(context.getString(R.string.label_feature_request)))
                return R.color.featureRequestBtn_A700;
            else if (sectionName.equals(context.getString(R.string.label_general_feedback)))
                return R.color.generalFeedbackBtn_900;
            else if (sectionName.equals(context.getString(R.string.label_bug_report)))
                return R.color.bugReportBtn_A700;
            else if (sectionName.equals(context.getString(R.string.label_contact_us)))
                return R.color.contactUsBtn_900;
            else
                throw new IllegalArgumentException("Invalid section name: " + sectionName);
        } else
            return customColorsList.get(position);
    }
}
