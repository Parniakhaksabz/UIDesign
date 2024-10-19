package com.example.myapplicationsdsdsd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private String currentInput = "";

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
                break;
            case "<<":
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                }
                break;
            case "=":
                // Logic for calculation
                calculateResult();
                return;
            default:
                currentInput += buttonText;
                break;
        }

        tvResult.setText(currentInput);
    }

    private void calculateResult() {
        // Simple calculation logic (use scripting engine or custom logic)
        try {
            double result = eval(currentInput);
            tvResult.setText(String.valueOf(result));
        } catch (Exception e) {
            tvResult.setText("Error");
        }
    }

    // Function to evaluate the mathematical expression
    public double eval(String expression) throws Exception {
        // Add evaluation logic here or use libraries
        return 0; // Placeholder
    }
}