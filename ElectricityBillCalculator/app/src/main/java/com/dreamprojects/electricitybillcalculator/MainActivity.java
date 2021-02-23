package com.dreamprojects.electricitybillcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNumber;
    EditText etName;
    EditText etPreviousReading;
    EditText etPresentReading;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = findViewById(R.id.edit_text_number);
        etName = findViewById(R.id.edit_text_name);
        etPreviousReading = findViewById(R.id.edit_text_previous);
        etPresentReading = findViewById(R.id.edit_text_present);
        btnSubmit = findViewById(R.id.button_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etNumber.getText().toString();
                String name = etName.getText().toString();
                String previousReading = etPreviousReading.getText().toString();
                String presentReading = etPresentReading.getText().toString();
                if (isAllFilled(number, name, previousReading, presentReading)) {
                    Bundle data = new Bundle();

                    data.putString("number", number);
                    data.putString("name", name);
                    data.putString("previousReading", previousReading);
                    data.putString("presentReading", presentReading);

                    Intent CalculationActivity = new Intent(MainActivity.this, CalculationActivity.class);
                    CalculationActivity.putExtras(data);
                    startActivity(CalculationActivity);
                }
            }
        });
    }

    public boolean isAllFilled(String number, String name, String previousReading, String presentReading) {
        if (number.trim().length() == 0) {
            etNumber.setError("Consumer Number Required");
            return false;
        }

        if (name.trim().length() == 0) {
            etName.setError("Consumer Name Required");
            return false;
        }

        if (previousReading.trim().length() == 0) {
            etPreviousReading.setError("Previous Reading Required");
            return false;
        }

        if (presentReading.trim().length() == 0) {
            etPresentReading.setError("Present Reading Required");
            return false;
        }

        if (Integer.parseInt(presentReading) < Integer.parseInt(previousReading)) {
            Toast.makeText(MainActivity.this
                    , "Present Reading Can't be less than Previous Reading"
                    , Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}