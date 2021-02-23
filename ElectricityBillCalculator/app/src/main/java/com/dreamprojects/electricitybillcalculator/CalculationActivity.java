package com.dreamprojects.electricitybillcalculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculationActivity extends AppCompatActivity {

    TextView txtNumber;
    TextView txtName;
    TextView txtUnitConsumed;
    TextView txtBillAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        txtNumber = findViewById(R.id.text_number);
        txtName = findViewById(R.id.text_name);
        txtUnitConsumed = findViewById(R.id.text_unit);
        txtBillAmount = findViewById(R.id.text_amount);

        Bundle data = getIntent().getExtras();

        if (data != null) {
            String number = data.getString("number");
            String name = data.getString("name");
            int previousReading = Integer.parseInt(data.getString("previousReading"));
            int presentReading = Integer.parseInt(data.getString("presentReading"));
            int unitConsumed = presentReading - previousReading;

            int amount = findBillAmount(unitConsumed);

            txtNumber.setText("Consumer Number: " + number);
            txtName.setText("Consumer Name: " + name);
            txtUnitConsumed.setText("Unit Consumed: " + unitConsumed);
            txtBillAmount.setText("Bill amount: " + amount);
        }
    }

    public int findBillAmount(int unitConsumed) {
        int amount = 0;
        if (unitConsumed < 200) {
            amount = unitConsumed * 2;
        } else {
            amount = unitConsumed * 3;
        }
        return amount;
    }
}