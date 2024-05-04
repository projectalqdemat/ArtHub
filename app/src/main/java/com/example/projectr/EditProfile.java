package com.example.projectr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfile extends AppCompatActivity {

    ImageButton back;
    Button done;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText PhoneEditText;
    ImageView imageView;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.editTextEmail);
        PhoneEditText = findViewById(R.id.editTextPhone);

        back = findViewById(R.id.imageButton);

        done = findViewById(R.id.done);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.floatingActionButton);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkCredentials();

                if (usernameEditText.getError() == null &&
                        emailEditText.getError() == null &&
                        PhoneEditText.getError() == null ) {
                    startActivity(new Intent(EditProfile.this, MainActivity.class));
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(EditProfile.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfile.this, ProfileActivity.class));

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode , data);
        Uri uri = data .getData();
        imageView.setImageURI(uri);
    }

    private void checkCredentials() {
        String userName = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = PhoneEditText.getText().toString();


        String emailPattern = "^[a-zA-Z0-9_]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        String validPrefix1 = "079";
        String validPrefix2 = "078";
        String validPrefix3 = "077";

        clearErrors(); // Clear any existing errors

        if (userName.isEmpty() || userName.length() < 5) {
            showError(usernameEditText, "Your Username is not valid!");
        }

        if (email.isEmpty() || !matcher.matches()) {
            showError(emailEditText, "Email is not valid!");
        }

        if (phone.length() != 10 || (!phone.startsWith(validPrefix1) && !phone.startsWith(validPrefix2) && !phone.startsWith(validPrefix3))) {
            showError(PhoneEditText, "Phone number must be 10 numbers and start with a valid prefix!");
        }

    }

    private void clearErrors() {
        usernameEditText.setError(null);
        emailEditText.setError(null);
        PhoneEditText.setError(null);
    }

    private void showError(EditText input, String errorMessage) {
        input.setError(errorMessage);
        input.requestFocus();
    }
}