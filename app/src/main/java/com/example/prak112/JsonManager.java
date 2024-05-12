package com.example.prak112;

import com.google.gson.Gson;

public class JsonManager {
    // Метод для разбора JSON-строки с использованием библиотеки Gson
    public void parseJsonUsingGson() {
        // JSON-строка, которую необходимо разобрать
        String jsonStr = "{\"name\":\"John\", \"age\":30, \"email\":\"john@example.com\"}";
        // Создаем объект Gson
        Gson gson = new Gson();
        // Преобразуем JSON-строку в объект User с помощью метода fromJson
        User user = gson.fromJson(jsonStr, User.class);
        // Выводим полученные данные в консоль
        System.out.println("Name: " + user.name);
        System.out.println("Age: " + user.age);
        System.out.println("Email: " + user.email);
    }
    // Метод для создания JSON-строки с использованием библиотеки Gson
    public String createJsonUsingGson() {
        // Создаем объект пользователя
        User user = new User();
        user.name = "Alice";
        user.age = 25;
        user.email = "alice@example.com";
        // Создаем объект Gson
        Gson gson = new Gson();
        // Преобразуем объект пользователя в JSON-строку с помощью метода toJson
        return gson.toJson(user);
    }
}

