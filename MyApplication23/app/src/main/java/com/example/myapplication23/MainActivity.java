package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// 1. Імплементуємо інтерфейс OnClickListener
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNext, btnCheck;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Знаходимо елементи за ID
        btnNext = findViewById(R.id.btnNext);
        btnCheck = findViewById(R.id.btnCheckSystem);
        tvStatus = findViewById(R.id.tvStatus);

        // 3. Призначаємо слухача (this - означає цей клас)
        btnNext.setOnClickListener(this);
        btnCheck.setOnClickListener(this);
    }

    // 4. Метод onClick, який вимагає інтерфейс
    @Override
    public void onClick(View v) {
        // Використовуємо if-else (або switch для старіших версій Java)
        int id = v.getId();

        if (id == R.id.btnNext) {
            // --- НАВІГАЦІЯ (Вимога завдання) ---
            Intent intent = new Intent(this, ActivityTwo.class);
            startActivity(intent);

        } else if (id == R.id.btnCheckSystem) {
            // --- БІЗНЕС-ЛОГІКА (Вимога завдання) ---
            tvStatus.setText("Статус: Системи в нормі! Паливо 100%");
            tvStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            Toast.makeText(this, "Діагностика завершена", Toast.LENGTH_SHORT).show();
        }
    }
}