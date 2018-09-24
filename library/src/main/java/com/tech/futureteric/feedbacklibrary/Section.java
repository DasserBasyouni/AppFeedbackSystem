package com.tech.futureteric.feedbacklibrary;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Section {

    public static class FrequentlyAskedQuestions extends Section {
        public List<String> questionsAndAnswersList;

        public FrequentlyAskedQuestions(List<String> questionsAndAnswersList) {
            this.questionsAndAnswersList = questionsAndAnswersList;
        }
    }

    public static class FeatureRequest extends Section {
        public FirebaseFirestore firebaseFirestore;
        public int userUid;

        public FeatureRequest(FirebaseFirestore firebaseFirestore, int userUid) {
            this.firebaseFirestore = firebaseFirestore;
            this.userUid = userUid;
        }

    }

    public static class GeneralFeedback extends Section {
        public String email;

        public GeneralFeedback(String email) {
            this.email = email;
        }
    }

    public static class BugReport extends Section {
        public String email;

        public BugReport(String email) {
            this.email = email;
        }
    }

    public static class ContactUs extends Section {
        public String email;

        public ContactUs(String email) {
            this.email = email;
        }
    }
}