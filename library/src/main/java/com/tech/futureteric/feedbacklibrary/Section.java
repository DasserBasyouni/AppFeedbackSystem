package com.tech.futureteric.feedbacklibrary;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Section {

    // TODO add Fab buttonts drawable to theme customization
    public static class FrequentlyAskedQuestions extends Section {
        public List<String> questionsAndAnswersList;
        public int theme;

        public FrequentlyAskedQuestions(List<String> questionsAndAnswersList) {
            this.questionsAndAnswersList = questionsAndAnswersList;
        }

        public void setTheme(int theme) {
            this.theme = theme;
        }
    }

    public static class FeatureRequest extends Section {
        public FirebaseFirestore firebaseFirestore;
        public int userUid, theme;

        public FeatureRequest(FirebaseFirestore firebaseFirestore, int userUid) {
            this.firebaseFirestore = firebaseFirestore;
            this.userUid = userUid;
        }

        public void setTheme(int theme) {
            this.theme = theme;
        }
    }

    public static class GeneralFeedback extends Section {
        public String email;
        public int theme;

        public GeneralFeedback(String email) {
            this.email = email;
        }

        public void setTheme(int theme) {
            this.theme = theme;
        }
    }

    public static class BugReport extends Section {
        public String email;
        public int theme;

        public BugReport(String email) {
            this.email = email;
        }

        public void setTheme(int theme) {
            this.theme = theme;
        }
    }

    public static class ContactUs extends Section {
        public String email;
        public int theme;

        public ContactUs(String email) {
            this.email = email;
        }

        public void setTheme(int theme) {
            this.theme = theme;
        }
    }

}