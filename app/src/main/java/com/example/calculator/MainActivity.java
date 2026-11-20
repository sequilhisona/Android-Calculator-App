package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * MainActivity handles user input, arithmetic operations (+, -, *, /, %, ^),
 * equation preview, input validation, and result formatting.
 */
public class MainActivity extends Activity {

    private EditText etNumber1;
    private EditText etNumber2;
    private TextView tvEquation;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        tvEquation = findViewById(R.id.tvEquation);
        tvResult = findViewById(R.id.tvResult);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnModulo = findViewById(R.id.btnModulo);
        Button btnPower = findViewById(R.id.btnPower);
        Button btnClear = findViewById(R.id.btnClear);

        btnAdd.setOnClickListener(v -> calculate("+", '+'));
        btnSubtract.setOnClickListener(v -> calculate("−", '-'));
        btnMultiply.setOnClickListener(v -> calculate("×", '*'));
        btnDivide.setOnClickListener(v -> calculate("÷", '/'));
        btnModulo.setOnClickListener(v -> calculate("%", '%'));
        btnPower.setOnClickListener(v -> calculate("^", '^'));

        btnClear.setOnClickListener(v -> {
            etNumber1.setText("");
            etNumber2.setText("");
            tvEquation.setText("Ready to calculate");
            tvResult.setText("0");
        });
    }

    private void calculate(String symbol, char operator) {
        String input1 = etNumber1.getText().toString().trim();
        String input2 = etNumber2.getText().toString().trim();

        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1;
        double num2;
        try {
            num1 = Double.parseDouble(input1);
            num2 = Double.parseDouble(input2);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
            return;
        }

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
                if (num2 == 0) {
                    tvEquation.setText(formatNumber(num1) + " ÷ 0");
                    tvResult.setText("Cannot divide by 0");
                    return;
                }
                result = num1 / num2;
                break;
            case '%':
                if (num2 == 0) {
                    tvEquation.setText(formatNumber(num1) + " % 0");
                    tvResult.setText("Cannot divide by 0");
                    return;
                }
                result = num1 % num2;
                break;
            case '^':
                result = Math.pow(num1, num2);
                break;
        }

        tvEquation.setText(formatNumber(num1) + " " + symbol + " " + formatNumber(num2) + " =");
        tvResult.setText(formatNumber(result));
    }

    private String formatNumber(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            return "Error";
        }
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
