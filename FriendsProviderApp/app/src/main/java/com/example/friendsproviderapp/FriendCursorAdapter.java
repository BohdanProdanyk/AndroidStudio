package com.example.friendsproviderapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class FriendCursorAdapter extends CursorAdapter {

    public FriendCursorAdapter(Context context, Cursor c) {
        // Використовуємо прапорець 0
        super(context, c, 0);
    }

    // Створює новий порожній View для рядка
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(
                R.layout.list_item_friend, parent, false);
    }

    // Прив'язує дані з Cursor до створеного View
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameText = view.findViewById(R.id.nameText);
        TextView contactText = view.findViewById(R.id.contactText);

        // 1. Отримуємо індекси стовпців
        int nameColumnIndex = cursor.getColumnIndex(FriendsContract.Columns.NAME);
        int emailColumnIndex = cursor.getColumnIndex(FriendsContract.Columns.EMAIL);
        int phoneColumnIndex = cursor.getColumnIndex(FriendsContract.Columns.PHONE);

        // 2. Витягуємо дані
        String friendName = cursor.getString(nameColumnIndex);
        String friendEmail = cursor.getString(emailColumnIndex);
        String friendPhone = cursor.getString(phoneColumnIndex);

        // 3. Відображаємо
        nameText.setText(friendName);

        // Відображаємо email, якщо є, інакше - телефон
        if (friendEmail != null && !friendEmail.isEmpty()) {
            contactText.setText("Email: " + friendEmail);
        } else {
            contactText.setText("Телефон: " + friendPhone);
        }
    }
}