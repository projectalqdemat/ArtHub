package com.example.projectr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;

import java.util.Objects;




public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;
    EditText searchEditText;
    ImageButton order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        searchEditText = findViewById(R.id.editTextText3);
        order = findViewById(R.id.OrderImageButton);








        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            } else if (itemId == R.id.profile) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }

            return true;
        });




        CardView cardProduct = findViewById(R.id.cardProduct);
        CardView cardService = findViewById(R.id.cardService);
        CardView cardCourse = findViewById(R.id.cardCourse);



        cardProduct.setOnClickListener(v -> {
            // Handle card click event here
            Toast.makeText(MainActivity.this, "Product", Toast.LENGTH_SHORT).show();
        });



        cardService.setOnClickListener(v -> {
            // Handle card click event here
            Toast.makeText(MainActivity.this, "Service", Toast.LENGTH_SHORT).show();
        });


        cardCourse.setOnClickListener(v -> {
            // Handle card click event here
            Toast.makeText(MainActivity.this, "Courses", Toast.LENGTH_SHORT).show();
        });

        order.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,CartActivity.class)));


       /*// Example: Adding a text change listener
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Before text changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Text changed
                String searchText = s.toString();
                // You can perform actions based on the search text here
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void afterTextChanged(Editlable s) {
                // After text changed
            }
        });*/



        /*

        This code to make requests, when the noor part is ready [Data Model Classes]remove the comment
        // Initialize repository
        MyRepository repository = MyRepository.getInstance(this);

        // Define API endpoint URL
        String url = "http://your-api-base-url.com/api/data";

        // Make API request
        repository.makeApiRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle successful response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });


                OR*/

                String url = "https://localhost:44325/api/test";
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                        response -> {
                       // Handle successful response
                             },
                        error -> {
                        // Handle error
                       });

             // Add the request to the RequestQueue
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);


    }




}