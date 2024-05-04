package com.example.projectr;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AdminPage extends AppCompatActivity {

     DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });


        drawerLayout=findViewById(R.id.drawer_layoutA);
        Toolbar toolbar=findViewById(R.id.toolbar);
        NavigationView navigationView= findViewById(R.id.nav_viewA);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        CardView cardProduct = findViewById(R.id.cardProduct);
        CardView cardService = findViewById(R.id.cardService);
        CardView cardCourse = findViewById(R.id.cardCourse);



        cardProduct.setOnClickListener(v -> {
            // Handle card click event here
            Toast.makeText(AdminPage.this, "Product", Toast.LENGTH_SHORT).show();
        });



        cardService.setOnClickListener(v -> {
            // Handle card click event here
            Toast.makeText(AdminPage.this, "Service", Toast.LENGTH_SHORT).show();
        });


        cardCourse.setOnClickListener(v -> {
            // Handle card click event here
            Toast.makeText(AdminPage.this, "Courses", Toast.LENGTH_SHORT).show();
        });


        navigationView.setNavigationItemSelectedListener(item -> {
            // Handle navigation view item clicks here
            int id = item.getItemId();
            if (id == R.id.nav_homeA) {
                // Handle the home menu item click
                startActivity(new Intent(AdminPage.this, AdminPage.class));

                return true;
            } else if (id == R.id.nav_regReqs) {
                // Handle the regReqs menu item click
                startActivity(new Intent(AdminPage.this, RegisterReqs.class));
            } else if (id == R.id.nav_postsReqs) {
                // Handle the postsReqs menu item click
                startActivity(new Intent(AdminPage.this, PostsReqs.class));
            } else if (id == R.id.nav_categories) {
                // Handle the categories menu item click
                startActivity(new Intent(AdminPage.this, Categories.class));

            }else if (id == R.id.nav_reports) {
                // Handle the reports menu item click
                startActivity(new Intent(AdminPage.this, Reports.class));
            }else if (id == R.id.nav_logout) {
                // Handle the logout menu item click
                startActivity(new Intent(AdminPage.this, GetStart.class));

            }
            // Close the drawer after handling the click
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }

    public void setSupportActionBar(Toolbar toolbar) {

    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}