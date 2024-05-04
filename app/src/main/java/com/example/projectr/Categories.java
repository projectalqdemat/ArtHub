package com.example.projectr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Categories extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        recyclerView = findViewById(R.id.category_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize with some categories
        List<Category> initialCategories = new ArrayList<>();
        initialCategories.add(new Category("1", "Handmade"));
        initialCategories.add(new Category("2", "Photograph"));

        adapter = new CategoryAdapter(initialCategories);
        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.add_category_button);
        EditText editTextName = findViewById(R.id.category_edit_text);
        addButton.setOnClickListener(v -> {
            String categoryName = editTextName.getText().toString().trim();
            if (!categoryName.isEmpty()) {
                Category newCategory = new Category(UUID.randomUUID().toString(), categoryName);
                adapter.addCategory(newCategory);
                editTextName.setText(""); // Clear the EditText
            }
        });

        Button deleteButton = findViewById(R.id.delete_category_button);
        EditText editTextId = findViewById(R.id.category_id_edit_text);
        deleteButton.setOnClickListener(v -> {
            try {
                int position = Integer.parseInt(editTextId.getText().toString());
                adapter.deleteCategory(position-1);
                editTextId.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid position", Toast.LENGTH_SHORT).show();
            }
        });
    }
}