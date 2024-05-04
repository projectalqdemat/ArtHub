package com.example.projectr;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseHelper {

    private FirebaseFirestore  db = FirebaseFirestore.getInstance();
    private CollectionReference usersCollection = db.collection("users");

    public interface UsernameCheckCallback {
        void onCallback(boolean isUnique);
    }

    public void isUsernameUnique(String username, UsernameCheckCallback callback) {
        usersCollection.whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult() != null && !task.getResult().isEmpty()) {

                            callback.onCallback(false);
                            // Username exists, it's not unique
                            // Handle the case where the username is not unique
                            // Show an error message or take appropriate action
                        } else {
                            callback.onCallback(true);
                            // Username is unique, proceed with your logic
                        }
                    }

                });
    }
}


