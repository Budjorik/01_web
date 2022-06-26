package ru.netology;

public class Main {
    public static void main(String[] args) {
        final int PORT = 9999; // Задаем номер порта http-сервера для подключения клиентов
        Server myServer = new Server(PORT);
        myServer.toStart();
    }
}
