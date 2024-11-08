package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer clickSound; // تعریف متغیر clickSound

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Balam", "09120000000", R.drawable.ic_profil1));
        contacts.add(new Contact("Parnia", "09170000000", R.drawable.ic_launcher_background));
        contacts.add(new Contact("Salivan", "09140000000", R.drawable.ic_profil1));
        contacts.add(new Contact("Barsam", "09160000000", R.drawable.ic_launcher_background));

        ContactAdapter adapter = new ContactAdapter(this, contacts);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        // ایجاد MediaPlayer برای پخش صدای کلیک
        clickSound = MediaPlayer.create(this, R.raw.click_sound);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // پخش صدای کلیک
                clickSound.start();

                Contact selectedContact = (Contact) parent.getItemAtPosition(position);
                String[] options = {"ارسال پیام", "تماس"};

                // نمایش دیالوگ برای انتخاب تماس یا پیام
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("انتخاب کنید:")
                        .setItems(options, (dialog, which) -> {
                            if (which == 0) {
                                // ارسال پیام
                                sendSMS(selectedContact.getPhoneNumber());
                                Toast.makeText(MainActivity.this, "پیام به " + selectedContact.getName() + " ارسال شد!", Toast.LENGTH_SHORT).show();
                            } else if (which == 1) {
                                // برقراری تماس
                                makeCall(selectedContact.getPhoneNumber());
                                Toast.makeText(MainActivity.this, "در حال برقراری تماس با " + selectedContact.getName(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    // متد ارسال پیام
    private void sendSMS(String phoneNumber) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phoneNumber, null));
        startActivity(smsIntent);
    }

    // متد تماس تلفنی
    private void makeCall(String phoneNumber) {
        // بررسی مجوز تماس تلفنی
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // آزاد کردن منابع MediaPlayer
        if (clickSound != null) {
            clickSound.release();
        }
    }
}
