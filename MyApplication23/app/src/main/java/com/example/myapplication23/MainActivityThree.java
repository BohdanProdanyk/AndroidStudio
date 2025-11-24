package com.example.myapplication23;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityThree extends AppCompatActivity implements View.OnClickListener {

    // Оголошуємо змінні
    Button btnShields, btnManeuver, btnNext;
    TextView tvReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_three);

        // 1. Знаходимо всі елементи
        btnShields = findViewById(R.id.btnShields);
        btnManeuver = findViewById(R.id.btnManeuver);
        btnNext = findViewById(R.id.btnToFour);
        tvReport = findViewById(R.id.tvDamageReport);

        // 2. Призначаємо слухача на ВСІ кнопки
        // Всі три кнопки будуть викликати один і той самий метод onClick внизу
        btnShields.setOnClickListener(this);
        btnManeuver.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId(); // Отримуємо ID натиснутої кнопки

        // 3. Перевіряємо, яка саме кнопка натиснута
        if (id == R.id.btnShields) {
            // Логіка: Щити
            tvReport.setText("Щити поглинули удар! Корпус цілий.");
            tvReport.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            Toast.makeText(this, "Захист спрацював!", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.btnManeuver) {
            // Логіка: Маневр
            tvReport.setText("Маневр успішний! Але ми втратили багато палива.");
            tvReport.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
            Toast.makeText(this, "Ухилення виконано!", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.btnToFour) {
            // Логіка: Перехід на наступний екран (Activity 4)
            Intent intent = new Intent(this, MainActivityFour.class);
            startActivity(intent);
        }
    }
}