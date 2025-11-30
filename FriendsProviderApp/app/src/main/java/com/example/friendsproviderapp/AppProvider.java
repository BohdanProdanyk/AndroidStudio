package com.example.friendsproviderapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AppProvider extends ContentProvider {

    private AppDatabase mOpenHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    // Коди для UriMatcher, щоб розрізняти типи запитів
    public static final int FRIENDS = 100;      // Запит до всієї таблиці
    public static final int FRIENDS_ID = 101;   // Запит до конкретного рядка по ID

    // Метод для створення та налаштування UriMatcher
    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        // content://com.example.friendsprovider/friends
        matcher.addURI(FriendsContract.CONTENT_AUTHORITY, FriendsContract.TABLE_NAME, FRIENDS);
        // content://com.example.friendsprovider/friends/8
        matcher.addURI(FriendsContract.CONTENT_AUTHORITY, FriendsContract.TABLE_NAME + "/#", FRIENDS_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        // Ініціалізуємо хелпер бази даних
        mOpenHelper = AppDatabase.getInstance(getContext());
        return true;
    }

    // --- READ (Отримання даних) ---
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        final int match = sUriMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch(match){
            case FRIENDS:
                // Запит до всієї таблиці
                queryBuilder.setTables(FriendsContract.TABLE_NAME);
                break;
            case FRIENDS_ID:
                // Запит до конкретного запису
                queryBuilder.setTables(FriendsContract.TABLE_NAME);
                long taskId = FriendsContract.getFriendId(uri);
                queryBuilder.appendWhere(FriendsContract.Columns._ID + " = " + taskId);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "+ uri);
        }

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        return queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
    }

    // --- Отримання MIME-типу даних ---
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch(match){
            case FRIENDS:
                return FriendsContract.CONTENT_TYPE;
            case FRIENDS_ID:
                return FriendsContract.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: "+ uri);
        }
    }

    // --- CREATE (Додавання даних) ---
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase db;
        Uri returnUri;
        long recordId;

        if (match == FRIENDS) {
            db = mOpenHelper.getWritableDatabase();
            recordId = db.insert(FriendsContract.TABLE_NAME, null, values);
            if (recordId > 0) {
                returnUri = FriendsContract.buildFriendUri(recordId);
            } else {
                throw new SQLException("Failed to insert: " + uri.toString());
            }
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return returnUri;
    }

    // --- DELETE (Видалення даних) ---
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String selectionCriteria = selection;

        if(match != FRIENDS && match != FRIENDS_ID)
            throw new IllegalArgumentException("Unknown URI: "+ uri);

        if(match == FRIENDS_ID) {
            long taskId = FriendsContract.getFriendId(uri);
            selectionCriteria = FriendsContract.Columns._ID + " = " + taskId;
            if ((selection != null) && (selection.length() > 0)) {
                selectionCriteria += " AND (" + selection + ")";
            }
        }
        return db.delete(FriendsContract.TABLE_NAME, selectionCriteria, selectionArgs);
    }

    // --- UPDATE (Оновлення даних) ---
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String selectionCriteria = selection;

        if(match != FRIENDS && match != FRIENDS_ID)
            throw new IllegalArgumentException("Unknown URI: "+ uri);

        if(match == FRIENDS_ID) {
            long taskId = FriendsContract.getFriendId(uri);
            selectionCriteria = FriendsContract.Columns._ID + " = " + taskId;
            if ((selection != null) && (selection.length() > 0)) {
                selectionCriteria += " AND (" + selection + ")";
            }
        }
        return db.update(FriendsContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
    }
}