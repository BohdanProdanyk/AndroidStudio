package com.example.friendsproviderapp;

import android.content.ContentUris;
import android.net.Uri;

public class FriendsContract {
    // Назва таблиці в БД
    static final String TABLE_NAME = "friends";

    // Унікальне ім'я провайдера (Authority). Має збігатися з тим, що буде в Manifest!
    static final String CONTENT_AUTHORITY = "com.example.friendsprovider";

    // Базовий URI для звернення до провайдера
    static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // MIME-типи даних (стандарт Android для курсорів)
    // Тип для списку записів (dir)
    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    // Тип для одного запису (item)
    static final String CONTENT_ITEM_TYPE= "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    // Вкладений клас, що описує стовпці таблиці
    public static class Columns{
        public static final String _ID = "_id"; // Обов'язкове поле для курсорів Android
        public static final String NAME = "Name";
        public static final String EMAIL = "Email";
        public static final String PHONE = "Phone";

        private Columns(){
            // Приватний конструктор, щоб заборонити створення екземплярів
        }
    }

    // Повний URI для доступу до таблиці friends
    static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    // Метод створює URI для конкретного запису за його ID (наприклад: .../friends/5)
    static Uri buildFriendUri(long taskId){
        return ContentUris.withAppendedId(CONTENT_URI, taskId);
    }

    // Метод витягує ID з URI
    static long getFriendId(Uri uri){
        return ContentUris.parseId(uri);
    }
}