package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityFour extends AppCompatActivity implements View.OnClickListener {

    // Оголошуємо змінні
    Button btnAnalyze, btnFinal;
    TextView tvAtmosphere;
    boolean isSafe = false; // Додаткова змінна для логіки (перевірка)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ВАЖЛИВО: Тут має бути саме твоя назва XML файлу
        setContentView(R.layout.activity_main_four);

        // 1. Знаходимо елементи
        btnAnalyze = findViewById(R.id.btnAnalyze);
        btnFinal = findViewById(R.id.btnEstablishBase);
        tvAtmosphere = findViewById(R.id.tvAtmosphere);

        // 2. Призначаємо слухача
        btnAnalyze.setOnClickListener(this);
        btnFinal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnAnalyze) {
            // --- ЛОГІКА: Аналіз ---
            tvAtmosphere.setText("Кисень виявлено! Планета придатна для життя.");
            // Використовуємо стандартний системний колір, щоб уникнути помилок
            tvAtmosphere.setTextColor(getResources().getColor(android.R.color.holo_green_dark));

            isSafe = true; // Запам'ятовуємо, що ми перевірили планету
            Toast.makeText(this, "Аналіз завершено успішно", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.btnEstablishBase) {
            // --- НАВІГАЦІЯ: Перехід до Фіналу ---
            if (isSafe) {
                Intent intent = new Intent(this, MainActivityFive.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Спочатку перевірте атмосферу!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}