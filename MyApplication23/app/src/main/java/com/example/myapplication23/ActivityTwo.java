package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {

    // Оголошуємо змінні для елементів інтерфейсу
    Button btnScan, btnNext;
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two); // Зв'язуємо з нашим XML

        // Знаходимо елементи за їх ID з XML файлу
        btnScan = findViewById(R.id.btnScan);
        btnNext = findViewById(R.id.btnToThree);
        tvStatus = findViewById(R.id.tvSpaceStatus);

        // Встановлюємо "слухача" (цей клас) на кнопки
        // Це означає: "Коли натиснуть кнопку, виклич метод onClick цього класу"
        btnScan.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Перевіряємо, яка саме кнопка була натиснута
        int id = v.getId();

        if (id == R.id.btnScan) {
            // --- ЛОГІКА: Сканування ---
            tvStatus.setText("УВАГА! Попереду пояс астероїдів!");
            tvStatus.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            Toast.makeText(this, "Радар виявив небезпеку!", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.btnToThree) {
            // --- НАВІГАЦІЯ: Перехід до Activity 3 ---
            Intent intent = new Intent(this, MainActivityThree.class);
            startActivity(intent);
        }
    }
}