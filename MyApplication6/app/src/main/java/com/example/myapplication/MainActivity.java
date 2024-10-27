package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextFamily;
    Button buttonEdit, buttonAdd, buttonDelete;

    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_FAMILY = "family";

    private MediaPlayer mediaPlayerAdd;
    private MediaPlayer mediaPlayerDelete;
    private MediaPlayer mediaPlayerEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextFamily = findViewById(R.id.editTextFamily);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        String savedName = sharedPreferences.getString(KEY_NAME, "");
        String savedFamily = sharedPreferences.getString(KEY_FAMILY, "");
        editTextName.setText(savedName);
        editTextFamily.setText(savedFamily);

        // بارگذاری صداها
        mediaPlayerAdd = MediaPlayer.create(this, R.raw.sound_add);
        mediaPlayerDelete = MediaPlayer.create(this, R.raw.sound_delete);
        mediaPlayerEdit = MediaPlayer.create(this, R.raw.sound_edit);

        // تنظیم Listener برای MediaPlayer
        mediaPlayerAdd.setOnCompletionListener(mp -> mp.reset());
        mediaPlayerDelete.setOnCompletionListener(mp -> mp.reset());
        mediaPlayerEdit.setOnCompletionListener(mp -> mp.reset());

        // عملکرد دکمه Add
        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String family = editTextFamily.getText().toString();

            if (!name.isEmpty() && !family.isEmpty()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, name);
                editor.putString(KEY_FAMILY, family);
                editor.apply();

                // پخش صدا
                if (mediaPlayerAdd != null) {
                    try {
                        mediaPlayerAdd.start();
                    } catch (Exception e) {
                        Log.e("MediaPlayerError", "Error playing add sound: " + e.getMessage());
                    }
                }
            }
        });

        // عملکرد دکمه Edit
        buttonEdit.setOnClickListener(v -> {
            if (mediaPlayerEdit != null) {
                try {
                    mediaPlayerEdit.start();
                } catch (Exception e) {
                    Log.e("MediaPlayerError", "Error playing edit sound: " + e.getMessage());
                }
            }
        });

        // عملکرد دکمه Delete با انیمیشن
        buttonDelete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // شروع انیمیشن حرکت
                        v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.button_move));
                        return true; // به این معناست که رویداد لمس پردازش شده است
                    case MotionEvent.ACTION_UP:
                        // در اینجا می‌توانید کد دکمه حذف را قرار دهید
                        editTextName.setText("");
                        editTextFamily.setText("");
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();

                        // پخش صدا
                        if (mediaPlayerDelete != null) {
                            try {
                                mediaPlayerDelete.start();
                            } catch (Exception e) {
                                Log.e("MediaPlayerError", "Error playing delete sound: " + e.getMessage());
                            }
                        }
                        return true;
                }
                return false;
            }
        });

        // پخش صدا در هنگام حذف هر کاراکتر
        editTextName.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_DEL)) {
                if (mediaPlayerDelete != null) {
                    try {
                        mediaPlayerDelete.start();
                    } catch (Exception e) {
                        Log.e("MediaPlayerError", "Error playing delete sound: " + e.getMessage());
                    }
                }
            }
            return false;
        });

        editTextFamily.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_DEL)) {
                if (mediaPlayerDelete != null) {
                    try {
                        mediaPlayerDelete.start();
                    } catch (Exception e) {
                        Log.e("MediaPlayerError", "Error playing delete sound: " + e.getMessage());
                    }
                }
            }
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayerAdd != null) {
            mediaPlayerAdd.release();
            mediaPlayerAdd = null;
        }
        if (mediaPlayerDelete != null) {
            mediaPlayerDelete.release();
            mediaPlayerDelete = null;
        }
        if (mediaPlayerEdit != null) {
            mediaPlayerEdit.release();
            mediaPlayerEdit = null;
        }
    }
}
