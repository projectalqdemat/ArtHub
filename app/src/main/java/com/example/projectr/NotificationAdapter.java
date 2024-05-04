package com.example.projectr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private final List<NotificationItem> notificationList;
    private final Context context;

    public NotificationAdapter(List<NotificationItem> notificationList, Context context) {
        this.notificationList = notificationList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationItem notificationItem = notificationList.get(position);
        holder.orderCountTextView.setText(String.valueOf(notificationItem.getOrderCount()));
        holder.notificationTextView.setText(notificationItem.getNotificationText());
        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement cancellation logic here
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView orderCountTextView;
        public TextView notificationTextView;
        public Button cancelButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderCountTextView = itemView.findViewById(R.id.order_count_text_view);
            notificationTextView = itemView.findViewById(R.id.notification_text_view);
            cancelButton = itemView.findViewById(R.id.cancel_button);
        }
    }
}

