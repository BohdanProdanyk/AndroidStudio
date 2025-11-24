package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityFive extends AppCompatActivity implements View.OnClickListener {

    Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_five);

        // 1. Знаходимо кнопку
        btnRestart = findViewById(R.id.btnRestart);

        // 2. Призначаємо слухача
        btnRestart.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRestart) {
            // Створюємо намір повернутися на найпершу активність
            Intent intent = new Intent(this, MainActivity.class);

            // --- ВАЖЛИВИЙ МОМЕНТ ---
            // Ці прапорці кажуть системі: "Видали всі попередні активності з пам'яті
            // і зроби MainActivity новою початковою точкою".
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            // Закриваємо поточну (п'яту) активність
            finish();
        }
    }
}