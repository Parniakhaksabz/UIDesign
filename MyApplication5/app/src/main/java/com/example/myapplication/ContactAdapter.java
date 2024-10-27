package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }
        TextView nameTextView = convertView.findViewById(R.id.contact_name);
        TextView phoneTextView = convertView.findViewById(R.id.contact_phone);
        ImageView imageView = convertView.findViewById(R.id.contact_image);

        nameTextView.setText(contact.getName());
        phoneTextView.setText(contact.getPhoneNumber());
        imageView.setImageResource(contact.getImageResId());

        return convertView;
    }
}
