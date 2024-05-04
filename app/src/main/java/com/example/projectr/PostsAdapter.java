package com.example.projectr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private final List<String> posts; // Assuming posts is a list of strings

    public PostsAdapter(List<String> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        // Get the post data for the current position
        String post = posts.get(position);

        // Bind the post data to the view holder
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        TextView textDescription;
        // Add more views if needed

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            textDescription = itemView.findViewById(R.id.textDescription);
            // Initialize other views here if needed
        }

        public void bind(String post) {
            // Assuming post is in a specific format where title and description are separated by a delimiter like "|"
            String[] parts = post.split("\\|");
            if (parts.length >= 2) {
                textDescription.setText(parts[1]);
            } else {
                // Handle the case where the post format is incorrect
                textDescription.setText(R.string.invalid_post_format);
            }
            // Set other views if needed
        }
    }
}
