package com.tech.futureteric.feedbacklibrary.database;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.adapter.FeatureRequestAdapter;
import com.tech.futureteric.feedbacklibrary.model.Forum;

import java.util.List;

public class FireStoreUtils {

    private final static String TAG = FireStoreUtils.class.getSimpleName();


    public static void listenToFeatureRequestListAndUpdateRV(Activity activity, FirebaseFirestore db
            , View rootView) {

        db.collection("FeedbackSystem/Forum/Requests").limit(20)
                .orderBy("vote", Query.Direction.DESCENDING)
                .addSnapshotListener(activity, (queryDocumentSnapshots, e) -> {

                    if (e != null) {
                        Log.w(TAG, "Listen failed: " + e);
                        return;
                    }

                    List<Forum> requests = null;
                    if (queryDocumentSnapshots != null)
                        requests = queryDocumentSnapshots.toObjects(Forum.class);

                    setupTheView(rootView, requests);
                });
    }

    public static void addFeatureRequest(FirebaseFirestore db,  Forum request) {
        db.collection("FeedbackSystem/FeatureRequest/Requests")
                .document().set(request)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Feature Request is successfully written!"))
                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
    }

    private static void setupTheView(View rootView, List<Forum> requests) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView_featureRequest);
        TextView textView = rootView.findViewById(R.id.textView_featureRequest);
        ProgressBar progressBar = rootView.findViewById(R.id.progressBar_featureRequest);

        if (requests == null || requests.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(new FeatureRequestAdapter(requests));
        }
    }

}
