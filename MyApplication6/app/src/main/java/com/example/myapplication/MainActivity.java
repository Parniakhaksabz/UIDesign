package com.example.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextFamily;
    Button buttonEdit, buttonAdd, buttonDelete;

    // نام فایل SharedPreferences
    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_FAMILY = "family";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextFamily = findViewById(R.id.editTextFamily);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);

        // دریافت SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // بازیابی اطلاعات ذخیره‌شده و تنظیم آن‌ها در فیلدهای ورودی
        String savedName = sharedPreferences.getString(KEY_NAME, "");
        String savedFamily = sharedPreferences.getString(KEY_FAMILY, "");
        editTextName.setText(savedName);
        editTextFamily.setText(savedFamily);

        // عملکرد دکمه Add
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String family = editTextFamily.getText().toString();

                if (!name.isEmpty() && !family.isEmpty()) {
                    // ذخیره‌سازی اطلاعات در SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME, name);
                    editor.putString(KEY_FAMILY, family);
                    editor.apply(); // ذخیره داده‌ها به صورت غیرهمزمان

                    Toast.makeText(MainActivity.this, "Information saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter both name and family", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // عملکرد دکمه Edit
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Edit clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // عملکرد دکمه Delete
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setText("");
                editTextFamily.setText("");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // پاک کردن تمام داده‌ها
                editor.apply();
                Toast.makeText(MainActivity.this, "Information cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
