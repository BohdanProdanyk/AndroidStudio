package com.example.myapplication54;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Створення списку продуктів
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Картопля", "кг."));
        products.add(new Product("Чай", "шт."));
        products.add(new Product("Яйця", "шт."));
        products.add(new Product("Молоко", "л."));
        products.add(new Product("Макарони", "кг."));

        // Знаходимо ListView
        ListView productList = findViewById(R.id.productList);

        // Створюємо адаптер
        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item, products);

        // Встановлюємо адаптер для списку
        productList.setAdapter(adapter);
    }
}