package com.example.prak112;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Метод, который вызывается при касании экрана
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Получаем менеджер ввода
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // Скрываем клавиатуру
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
    // Метод для добавления деталей
    public void onClickAddDetails(View view) {
        // Создаем объект ContentValues для хранения данных
        ContentValues values = new ContentValues();
        // Получаем имя из EditText и помещаем его в ContentValues
        values.put(MyContentProvider.name, ((EditText) findViewById(R.id.textName)).getText().toString());
        // Вставляем данные в провайдер контента
        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
        // Показываем сообщение о добавлении новой записи
        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
    }
    // Метод для показа деталей
    @SuppressLint("Range")
    public void onClickShowDetails(View view) {
        // Получаем TextView для отображения результата
        TextView resultView = (TextView) findViewById(R.id.result);
        // Выполняем запрос к провайдеру контента для получения данных
        Cursor cursor = getContentResolver().query(Uri.parse
                ("content://com.demo.user.provider/users"),
                null, null, null, null);
        // Проверяем, есть ли данные в результате запроса
        if (cursor.moveToFirst()) {
            // Если есть, строим строку с результатом
            StringBuilder strBuild = new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n" + cursor.getString(cursor.getColumnIndex("id")) + "-" + cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            // Отображаем результат в TextView
            resultView.setText(strBuild);
        } else {
            // Если нет данных, показываем соответствующее сообщение
            resultView.setText("No Records Found");
        }
    }
}