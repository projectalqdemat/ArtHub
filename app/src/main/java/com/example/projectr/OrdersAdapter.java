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

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private final List<Order> orders;
    private final Context context;

    // Constructor
    public OrdersAdapter(Context context, List<Order>  orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position); // Get Order object from the list
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    // View holder class
    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderCountTextView;
        View statusIndicator1;
        View statusIndicator2;
        View statusIndicator3;
        Button cancelButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderCountTextView = itemView.findViewById(R.id.order_count_text_view);
            statusIndicator1 = itemView.findViewById(R.id.status_indicator_1);
            statusIndicator2 = itemView.findViewById(R.id.status_indicator_2);
            statusIndicator3 = itemView.findViewById(R.id.status_indicator_3);
            cancelButton = itemView.findViewById(R.id.cancel_button);
        }

        public void bind(final Order order) {
            // Bind order data to views
            String orderNumberText = context.getString(R.string.order_number_text, Order.getOrderNumber());
            orderCountTextView.setText(orderNumberText);

            // Update status indicators based on order status
            updateStatusIndicators(Order.getStatus());

            // Set click listener for cancel button
            cancelButton.setOnClickListener(v -> {
                // Handle cancel button click
                // You can implement the cancel logic here
                cancelOrder(order);
            });
        }

        private void cancelOrder(Order order) {
            // Implement cancel logic here
            // Find the position of the order in the list
            int position = orders.indexOf(order);
            if (position != -1) {
                // Remove the order from the list
                orders.remove(position);
                // Notify the adapter that an item has been removed
                notifyItemRemoved(position);
            }
        }


        private void updateStatusIndicators(int status) {
            // Update status indicators based on order status
            switch (status) {
                case Order.STATUS_APPROVED:
                    // Set green indicator
                    statusIndicator1.setBackgroundResource(R.drawable.circle_green);
                    statusIndicator2.setBackgroundResource(R.drawable.circle_black);
                    statusIndicator3.setBackgroundResource(R.drawable.circle_black);
                    break;
                case Order.STATUS_REJECTED:
                    // Set red indicator
                    statusIndicator1.setBackgroundResource(R.drawable.circle_black);
                    statusIndicator2.setBackgroundResource(R.drawable.circle_red);
                    statusIndicator3.setBackgroundResource(R.drawable.circle_black);
                    break;
                case Order.STATUS_PENDING:
                    // Set gray indicator
                    statusIndicator1.setBackgroundResource(R.drawable.circle_black);
                    statusIndicator2.setBackgroundResource(R.drawable.circle_black);
                    statusIndicator3.setBackgroundResource(R.drawable.circle_gray);
                    break;
            }
        }
    }
}

