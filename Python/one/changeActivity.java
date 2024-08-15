package com.devskiller.anonymizer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ChangeActivity extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mSkypeEditText;
    private EditText mPhoneEditText;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        mEmailEditText = findViewById(R.id.edit_email);
        mSkypeEditText = findViewById(R.id.edit_skype);
        mPhoneEditText = findViewById(R.id.edit_phone);
        mSaveButton = findViewById(R.id.save_button);

        Data data = Data.get();

        // Populate fields with existing data
        mEmailEditText.setText(data.getEmail());
        mSkypeEditText.setText(data.getSkype());
        mPhoneEditText.setText(data.getPhone());

        mSaveButton.setOnClickListener(v -> {
            boolean valid = true;

            // Validate Email
            String email = mEmailEditText.getText().toString();
            if (email.isEmpty()) {
                mEmailEditText.setError(getString(R.string.field_cannot_be_empty_error));
                valid = false;
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mEmailEditText.setError(getString(R.string.invalid_email));
                valid = false;
            }

            // Validate Skype
            String skype = mSkypeEditText.getText().toString();
            if (skype.isEmpty()) {
                mSkypeEditText.setError(getString(R.string.field_cannot_be_empty_error));
                valid = false;
            } else if (skype.length() < 6) { // Assuming Skype usernames should be at least 6 characters
                mSkypeEditText.setError(getString(R.string.invalid_skype));
                valid = false;
            }

            // Validate Phone
            String phone = mPhoneEditText.getText().toString();
            if (phone.isEmpty()) {
                mPhoneEditText.setError(getString(R.string.field_cannot_be_empty_error));
                valid = false;
            } else if (phone.length() < 10) { // Assuming phone numbers should be at least 10 digits
                mPhoneEditText.setError(getString(R.string.invalid_phone));
                valid = false;
            }

            if (valid) {
                // Save data
                data.setEmail(email);
                data.setSkype(skype);
                data.setPhone(phone);
                finish();
            }
        });
    }
}
