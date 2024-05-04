package com.example.projectr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PostsReqs extends AppCompatActivity {

    private Button acceptButton, rejectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_reqs);

        // Initialize buttons
        acceptButton = findViewById(R.id.accept_button);
        rejectButton = findViewById(R.id.reject_button);

        // Set onClickListeners for accept and reject buttons
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle accept button click
                acceptPost();
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle reject button click
                rejectPost();
            }
        });
    }

    private void acceptPost() {
        // TODO: Implement logic to accept the post
        Toast.makeText(this, "Post Accepted", Toast.LENGTH_SHORT).show();
    }

    private void rejectPost() {
        // TODO: Implement logic to reject the post
        Toast.makeText(this, "Post Rejected", Toast.LENGTH_SHORT).show();
    }
}
