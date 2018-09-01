package com.tech.futureteric.feedbacklibrary;

import com.tech.futureteric.feedbacklibrary.constants.Sections;

public class FeedbackSystemBuilder {

    private Sections[] mSections;


    public FeedbackSystemBuilder() {}


    public FeedbackSystemBuilder withSections(Sections... sections){
        mSections = sections;
        return this;
    }

}
