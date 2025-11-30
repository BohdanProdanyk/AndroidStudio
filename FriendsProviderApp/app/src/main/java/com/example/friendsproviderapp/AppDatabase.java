package com.example.friendsproviderapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "friends.db";
    public static final int DATABASE_VERSION = 1;

    // Singleton instance
    private static AppDatabase instance = null;

    // Приватний конструктор (Singleton)
    private AppDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Метод для отримання єдиного екземпляра БД
    static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = new AppDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1. Створення таблиці
        String sql = "CREATE TABLE " + FriendsContract.TABLE_NAME + "(" +
                FriendsContract.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                FriendsContract.Columns.NAME + " TEXT NOT NULL, " +
                FriendsContract.Columns.EMAIL + " TEXT, " +
                FriendsContract.Columns.PHONE + " TEXT NOT NULL)";
        db.execSQL(sql);

        // 2. Додавання початкових даних (Mock data)
        // Додаємо Tom
        db.execSQL("INSERT INTO "+ FriendsContract.TABLE_NAME +" ("
                + FriendsContract.Columns.NAME + ", "
                + FriendsContract.Columns.PHONE + ") VALUES ('Tom', '+12345678990');");

        // Додаємо Bob
        db.execSQL("INSERT INTO "+ FriendsContract.TABLE_NAME +" ("
                + FriendsContract.Columns.NAME + ", "
                + FriendsContract.Columns.EMAIL + ", "
                + FriendsContract.Columns.PHONE + ") VALUES ('Bob', 'bob@gmail.com', '+13456789102');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Тут зазвичай пишеться логіка оновлення БД (наприклад, DROP TABLE IF EXISTS...)
        // Поки що залишаємо пустим, бо версія 1.
        db.execSQL("DROP TABLE IF EXISTS " + FriendsContract.TABLE_NAME);
        onCreate(db);
    }
}