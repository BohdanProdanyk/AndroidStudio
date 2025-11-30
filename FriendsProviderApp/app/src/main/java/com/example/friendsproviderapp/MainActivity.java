package com.example.friendsproviderapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FriendCursorAdapter mFriendAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.friendListView);

        // 1. Створюємо порожній адаптер
        mFriendAdapter = new FriendCursorAdapter(this, null);
        mListView.setAdapter(mFriendAdapter);

        // 2. Завантажуємо дані
        loadFriendsData();
    }

    /**
     * Виконує запит (QUERY) до ContentProvider і оновлює список.
     */
    private void loadFriendsData() {
        Log.d(TAG, "Виконуємо запит до провайдера...");

        // Використовуємо ContentResolver для запиту даних
        // URI: FriendsContract.CONTENT_URI (адреса таблиці 'friends')
        Cursor cursor = getContentResolver().query(
                FriendsContract.CONTENT_URI,
                null, // Projection (всі стовпці)
                null, // Selection (всі рядки)
                null, // Selection Args
                FriendsContract.Columns.NAME); // Sort Order (сортування за ім'ям)

        if (cursor != null) {
            // Оновлюємо курсор в адаптері
            mFriendAdapter.changeCursor(cursor);
            Log.d(TAG, "Завантажено " + cursor.getCount() + " записів.");
        } else {
            Log.e(TAG, "Помилка при отриманні курсора.");
            Toast.makeText(this, "Помилка завантаження даних!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onAddFriendClick(View view) {
        // Створюємо об'єкт ContentValues для нових даних
        ContentValues values = new ContentValues();
        values.put(FriendsContract.Columns.NAME, "New Friend");
        values.put(FriendsContract.Columns.EMAIL, "new@example.com");
        values.put(FriendsContract.Columns.PHONE, "+00000000000");

        // Використовуємо ContentResolver для вставки даних
        Uri uri = getContentResolver().insert(FriendsContract.CONTENT_URI, values);

        if (uri != null) {
            Toast.makeText(this, "Друг доданий успішно!", Toast.LENGTH_SHORT).show();
            // Перезавантажуємо список, щоб побачити новий запис
            loadFriendsData();
        } else {
            Toast.makeText(this, "Помилка вставки!", Toast.LENGTH_SHORT).show();
        }
    }
}