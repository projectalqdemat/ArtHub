package com.example.projectr;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.orders_recycler_view);

        // Initialize the list of orders (you may retrieve it from somewhere, like a database)
        List<Order> orders = new ArrayList<>();
        // Add orders to the list

        // Initialize adapter and set it to RecyclerView
        OrdersAdapter ordersAdapter = new OrdersAdapter(this, orders); // Assigning to ordersAdapter field

        // Set OrdersAdapter as the adapter for the RecyclerView
        recyclerView.setAdapter(ordersAdapter); // Using ordersAdapter

        // Set layout manager for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}