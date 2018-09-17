package com.tech.futureteric.feedbacklibrary;

import java.util.List;

public class Section {

    public static class FrequentlyAskedQuestions extends Section {
        List<String> questionsAndAnswersList;

        public FrequentlyAskedQuestions(List<String> questionsAndAnswersList) {
            this.questionsAndAnswersList = questionsAndAnswersList;
        }
    }

    public static class FeatureRequest extends Section {

        public FeatureRequest(Object o) {

        }

    }

    public static class GeneralFeedback extends Section {
        String email;

        public GeneralFeedback(String email) {
            this.email = email;
        }
    }

    public static class BugReport extends Section {
        String email;

        public BugReport(String email) {
            this.email = email;
        }
    }

    public static class ContactUs extends Section {
        String email;

        public ContactUs(String email) {
            this.email = email;
        }
    }
}