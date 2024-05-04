package com.example.projectr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    private List<String> feedbackList;

    public FeedbackAdapter(List<String> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {

        private TextView feedbackTextView;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            feedbackTextView = itemView.findViewById(R.id.feedbackTextView);
        }

        public void bind(int position) {
            String feedback = feedbackList.get(position);
            feedbackTextView.setText(feedback);
        }
    }
}

