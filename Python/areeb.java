// Anonymizer.java
package com.devskiller.anonymizer;

public class Anonymizer {

    public static String anonymizeEmail(String email, char replacement) {
        int atIndex = email.indexOf("@");
        if (atIndex > 0) {
            StringBuilder anonymizedEmail = new StringBuilder();
            for (int i = 0; i < atIndex; i++) {
                anonymizedEmail.append(replacement);
            }
            anonymizedEmail.append(email.substring(atIndex));
            return anonymizedEmail.toString();
        }
        return email; // Return the original if something goes wrong
    }

    public static String anonymizeSkype(String skype, char replacement) {
        StringBuilder anonymizedSkype = new StringBuilder();
        for (int i = 0; i < skype.length(); i++) {
            anonymizedSkype.append(replacement);
        }
        return anonymizedSkype.toString();
    }

    public static String anonymizePhone(String phone, char replacement, int digits) {
        int length = phone.length();
        if (length > digits) {
            StringBuilder anonymizedPhone = new StringBuilder(phone.substring(0, length - digits));
            for (int i = 0; i < digits; i++) {
                anonymizedPhone.append(replacement);
            }
            return anonymizedPhone.toString();
        }
        return phone; // Return the original if something goes wrong
    }
}


// MainActivity.java
package com.devskiller.anonymizer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mEmailTextView;
    private TextView mSkypeTextView;
    private TextView mPhoneTextView;
    private Button mEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailTextView = findViewById(R.id.emailTextView);
        mSkypeTextView = findViewById(R.id.skypeTextView);
        mPhoneTextView = findViewById(R.id.phoneTextView);
        mEditButton = findViewById(R.id.editButton);

        // Set the text views with the current data from the Data singleton
        mEmailTextView.setText(Data.get().getEmail());
        mSkypeTextView.setText(Data.get().getSkype());
        mPhoneTextView.setText(Data.get().getPhone());

        mEditButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChangeActivity.class);
            startActivity(intent);
        });
    }
}



// ChangeActivity.java
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChangeActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText skypeEditText;
    private EditText phoneEditText;
    private Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        emailEditText = findViewById(R.id.emailEditText);
        skypeEditText = findViewById(R.id.skypeEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        saveButton = findViewById(R.id.saveButton);

        // Set the current data from the Data singleton
        emailEditText.setText(Data.get().getEmail());
        skypeEditText.setText(Data.get().getSkype());
        phoneEditText.setText(Data.get().getPhone());

        saveButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String skype = skypeEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();

            if (email.isEmpty() || skype.isEmpty()) {
                Toast.makeText(ChangeActivity.this, getString(R.string.empty_error), Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidEmail(email)) {
                emailEditText.setError(getString(R.string.invalid_email));
                return;
            }

            if (!isValidSkype(skype)) {
                skypeEditText.setError(getString(R.string.invalid_skype));
                return;
            }

            if (!isValidPhone(phone)) {
                phoneEditText.setError(getString(R.string.invalid_phone));
                return;
            }

            // Save data and finish
            Data.get().setEmail(email);
            Data.get().setSkype(skype);
            Data.get().setPhone(phone);

            finish(); // Close the activity and return to MainActivity
        });
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailPattern);
    }

    private boolean isValidSkype(String skype) {
        String skypePattern = "^[a-zA-Z0-9]+$";
        return skype.matches(skypePattern);
    }

    private boolean isValidPhone(String phone) {
        String phonePattern = "^\\+[0-9]{1,3} [0-9]{3} [0-9]{3} [0-9]{3}$";
        return phone.matches(phonePattern);
    }
}