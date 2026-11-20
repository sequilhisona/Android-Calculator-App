package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * MainActivity handles user input, arithmetic operations (+, -, *, /),
 * input validation, and result display for the Calculator application.
 */
public class MainActivity extends Activity {

    // Input fields and result display TextView
    private EditText etNumber1;
    private EditText etNumber2;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the XML layout defined in res/layout/activity_main.xml
        setContentView(R.layout.activity_main);

        // Bind XML views to Java objects using their respective IDs
        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        tvResult = findViewById(R.id.tvResult);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnClear = findViewById(R.id.btnClear);

        // Attach click listeners for each arithmetic operation
        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSubtract.setOnClickListener(v -> calculate('-'));
        btnMultiply.setOnClickListener(v -> calculate('*'));
        btnDivide.setOnClickListener(v -> calculate('/'));

        // Reset all input fields and restore default result text
        btnClear.setOnClickListener(v -> {
            etNumber1.setText("");
            etNumber2.setText("");
            tvResult.setText("0");
        });
    }

    /**
     * Validates both numeric inputs, executes the selected arithmetic operation,
     * and formats the output cleanly inside tvResult.
     */
    private void calculate(char operator) {
        String input1 = etNumber1.getText().toString().trim();
        String input2 = etNumber2.getText().toString().trim();

        // Prevent empty input crashes
        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(input1);
        double num2 = Double.parseDouble(input2);
        double result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                // Handle division by zero safely
                if (num2 == 0) {
                    tvResult.setText("Cannot divide by 0");
                    return;
                }
                result = num1 / num2;
                break;
        }

        // Display whole numbers without trailing ".0"
        if (result == (long) result) {
            tvResult.setText(String.valueOf((long) result));
        } else {
            tvResult.setText(String.valueOf(result));
        }
    }
}
