package com.example.anonymizerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editButton = findViewById(R.id.editButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ChangeActivity
                Intent intent = new Intent(MainActivity.this, ChangeActivity.class);
                startActivity(intent);
            }
        });
    }
}


package com.example.anonymizerapp;

public class Anonymizer {
    private static final String REPLACEMENT = "**";

    public static String anonymizeEmail(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex > 0) {
            return REPLACEMENT + email.substring(atIndex);
        }
        return email; // Return the original if something goes wrong
    }

    public static String anonymizeSkype(String skype) {
        return skype.replaceAll(".", REPLACEMENT);
    }

    public static String anonymizePhone(String phone, int digitsToAnonymize) {
        int length = phone.length();
        if (length > digitsToAnonymize) {
            return phone.substring(0, length - digitsToAnonymize) + REPLACEMENT;
        }
        return phone; // Return the original if something goes wrong
    }
}



package com.example.anonymizerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ChangeActivity extends AppCompatActivity {
    private EditText emailEditText, skypeEditText, phoneEditText;
    private Button saveButton;
    private Data data; // Use Data class to hold the email, skype, and phone

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        emailEditText = findViewById(R.id.emailEditText);
        skypeEditText = findViewById(R.id.skypeEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        saveButton = findViewById(R.id.saveButton);

        // Initialize data - this is an example, update according to your needs
        data = new Data("example@example.com", "username", "+123 456 789 000");

        // Set current data
        emailEditText.setText(data.getEmail());
        skypeEditText.setText(data.getSkype());
        phoneEditText.setText(data.getPhone());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String skype = skypeEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();

                if (email.isEmpty() || skype.isEmpty()) {
                    // Show empty field error
                    emailEditText.setError(getString(R.string.empty_error));
                    skypeEditText.setError(getString(R.string.empty_error));
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

                // Save data and return to MainActivity
                data.setEmail(email);
                data.setSkype(skype);
                data.setPhone(phone);
                finish(); // Close the activity
            }
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
