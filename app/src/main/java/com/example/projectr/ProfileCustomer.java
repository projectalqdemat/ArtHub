package com.example.projectr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfileCustomer extends AppCompatActivity {

    private EditText enterFeedbackEditText;
    private Button addFeedbackButton;
    private Button whatsUP;
    private RecyclerView recyclerView;
    private FeedbackAdapter feedbackAdapter;
    private List<String> feedbackList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_customer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.customer_profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.posts_recycler_view);
        View layoutFeedback = findViewById(R.id.layoutfeedback);
        enterFeedbackEditText = layoutFeedback.findViewById(R.id.EnterFeedback);
        addFeedbackButton = layoutFeedback.findViewById(R.id.addFeedbackButton);
        whatsUP = findViewById(R.id.whatsUp);


        feedbackList = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(feedbackList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(feedbackAdapter);

        whatsUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addFeedbackButton.setOnClickListener(v -> addFeedback());
    }

    private void addFeedback() {
        String feedback = enterFeedbackEditText.getText().toString().trim();
        if (!feedback.isEmpty()) {
            feedbackList.add(feedback);
            feedbackAdapter.notifyDataSetChanged();
            enterFeedbackEditText.getText().clear();
        }
    }
}