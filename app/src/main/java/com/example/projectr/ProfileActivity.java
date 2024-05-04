package com.example.projectr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




public class ProfileActivity extends AppCompatActivity {

    FloatingActionButton fab;
    FloatingActionButton fab_feedback;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Button whatsUP;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);



        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        fab_feedback = findViewById(R.id.feedback_fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        RecyclerView recyclerView = findViewById(R.id.posts_recycler_view);
        whatsUP = findViewById(R.id.whatsUp);




        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);// if you don't want action bar set it to null
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
            } else if (itemId == R.id.profile) {
                startActivity(new Intent(ProfileActivity.this,ProfileActivity.class));
            }

            return true;
        });

        fab_feedback.setOnClickListener(view -> showBottomDialog());



        // Create and set layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Assuming you have a list of posts
        List<String> posts = new ArrayList<>();
        posts.add("Post 1");
        posts.add("Post 2");

        // Initialize adapter and set it to RecyclerView
        PostsAdapter adapter = new PostsAdapter(posts);
        recyclerView.setAdapter(adapter);



        navigationView.setNavigationItemSelectedListener(item -> {
            // Handle navigation view item clicks here
            int id = item.getItemId();
            if (id == R.id.nav_phone) {
                // Handle the phone menu item click
                showPhonePrivacyDialog();

                return true;
            } else if (id == R.id.nav_edit) {
                // Handle the edit menu item click
                startActivity(new Intent(ProfileActivity.this, EditProfile.class));
            } else if (id == R.id.nav_about) {
                // Handle the about menu item click
                startActivity(new Intent(ProfileActivity.this, AboutUs.class));
            } else if (id == R.id.nav_logout) {
                // Handle the logout menu item click
                startActivity(new Intent(ProfileActivity.this, GetStart.class));

            }else if (id == R.id.nav_share) {
                // Handle the logout menu item click
                sharePageLink();

            }
            // Close the drawer after handling the click
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void showPhonePrivacyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_phone_privacy, null);
        builder.setView(dialogView);

        SwitchCompat switchPrivacy = dialogView.findViewById(R.id.switchPrivacy);

        // Retrieve saved state of the Switch from SharedPreferences
        boolean isPublic = sharedPreferences.getBoolean(getString(R.string.pref_key_phone_privacy), false);
        switchPrivacy.setChecked(isPublic);

        builder.setPositiveButton("Save", (dialog, which) -> {
            boolean isPublicSelected = switchPrivacy.isChecked();

            // Save user's preference using SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getString(R.string.pref_key_phone_privacy), isPublicSelected);
            editor.apply();

            // Update UI or perform any other actions based on the user's choice
            if (isPublicSelected) {
                whatsUP.setEnabled(true); // Update UI if switch is checked
            } else {
                whatsUP.setEnabled(false); // Clear UI if switch is not checked
            }
        });

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }






    private void showBottomDialog() {

        final Dialog dialog = getDialog();


        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    @NonNull
    private Dialog getDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

        LinearLayout feedbackLayout = dialog.findViewById(R.id.layoutFeedback);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        feedbackLayout.setOnClickListener(v -> {

            dialog.dismiss();
            Toast.makeText(ProfileActivity.this,"Feedback",Toast.LENGTH_SHORT).show();

        });


        cancelButton.setOnClickListener(view -> dialog.dismiss());
        return dialog;
    }

    //if you click outside the drawer the drawer will close
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void sharePageLink() {
        // Assuming your app uses deep linking, construct a deep link to the current page
        String deepLink = "art_hub://page"; // art_hub is defined in the AndroidManifest.xml in the <intent-filter>


        // Create a ShareCompat.IntentBuilder to create the share intent
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Share Page Link") // Title of the share dialog
                .setText(deepLink) // Deep link to the current page
                .startChooser(); // Show the share dialog
    }









}