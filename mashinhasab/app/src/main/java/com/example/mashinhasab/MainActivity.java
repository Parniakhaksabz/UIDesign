package com.example.mashinhasab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                currentInput = "";
                firstNumber = 0;
                operator = "";
                break;
            case "=":
                calculateResult();
                return;
            case "+":
            case "-":
            case "×":
            case "÷":
                operator = buttonText;
                firstNumber = Double.parseDouble(currentInput);
                currentInput = "";
                break;
            default:
                currentInput += buttonText;
                break;
        }

        tvResult.setText(currentInput);
    }

    private void calculateResult() {
        double secondNumber = Double.parseDouble(currentInput);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    tvResult.setText("Error");
                    return;
                }
                break;
        }

        tvResult.setText(String.valueOf(result));
        currentInput = String.valueOf(result);  // برای ادامه محاسبات
    }
}