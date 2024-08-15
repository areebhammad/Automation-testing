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

        mEmailTextView = findViewById(R.id.email);
        mSkypeTextView = findViewById(R.id.skype);
        mPhoneTextView = findViewById(R.id.phone);
        mEditButton = findViewById(R.id.edit_button);

        Data data = Data.get();

        // Anonymize data before displaying
        mEmailTextView.setText(Anonymizer.anonymizeEmail(data.getEmail(), '*'));
        mSkypeTextView.setText(Anonymizer.anonymizeSkype(data.getSkype(), '*'));
        mPhoneTextView.setText(Anonymizer.anonymizePhone(data.getPhone(), '*', 3));

        mEditButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChangeActivity.class);
            startActivity(intent);
        });
    }
}
