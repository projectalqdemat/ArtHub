package com.example.projectr;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
public class ProfileCustomer extends AppCompatActivity {
    private FloatingActionButton addFeedbackButton;
    private BottomSheetDialog bottomSheetDialog;
    private FeedbackAdapter feedbackAdapter;
    private List<String> feedbackList;
    ImageButton back;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_customer);
        back = findViewById(R.id.backToHomeC);
        NavigationView navigationView = findViewById(R.id.nav_view_customer);
        drawerLayout = findViewById(R.id.customer_profile);
        addFeedbackButton = findViewById(R.id.addFeedbackButton);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        addFeedbackButton.setOnClickListener(v -> showBottomSheetDialog());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileCustomer.this, MainActivity.class));
            }
        });
        feedbackList = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(feedbackList);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_about) {
                startActivity(new Intent(ProfileCustomer.this, AboutUs.class));
            }else if (id == R.id.nav_share) {
                sharePageLink();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
    private void showBottomSheetDialog() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_customer, null);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomSheetView);
        RecyclerView recyclerView = bottomSheetView.findViewById(R.id.feedback_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(feedbackAdapter);
        EditText enterFeedbackEditText = bottomSheetView.findViewById(R.id.EnterFeedback);
        Button submitFeedbackButton = bottomSheetView.findViewById(R.id.shareFeedbackButton);
        submitFeedbackButton.setOnClickListener(v -> {
            String feedback = enterFeedbackEditText.getText().toString().trim();
            if (!feedback.isEmpty()) {
                feedbackList.add(feedback);
                feedbackAdapter.notifyDataSetChanged();
                enterFeedbackEditText.setText("");
            }
        });
        bottomSheetDialog.show();
    }
    private void sharePageLink() {
        String deepLink = "art_hub://page";
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Share Page Link")
                .setText(deepLink)
                .startChooser();
    }
}
