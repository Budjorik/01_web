package ru.netology;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        final int PORT = 9999; // Задаем номер порта http-сервера для подключения клиентов
        final String methodOne = "GET";
        final String fullPathOne = "C:/Users/karau/IdeaProjects/01_web/public/spring.svg";
        final String fullPathTwo = "C:/Users/karau/IdeaProjects/01_web/public/classic.html";
        final String fullPathThree = "C:/Users/karau/IdeaProjects/01_web/public/index.html";
        final String fullPathFour = "C:/Users/karau/IdeaProjects/01_web/public/spring.png";
        Server myServer = new Server();

        // Добавление 1-го handler (обработчика)
        myServer.addHandler(methodOne, fullPathOne, new Handler<BufferedOutputStream>() {
            public void handle(BufferedOutputStream responseStream) throws IOException {
                final var filePath = Path.of(fullPathOne);
                final var mimeType = Files.probeContentType(filePath);
                final var length = Files.size(filePath);
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                Files.copy(filePath, responseStream);
                responseStream.flush();
            }
        });

        // Добавление 2-го handler (обработчика)
        myServer.addHandler(methodOne, fullPathTwo, new Handler<BufferedOutputStream>() {
            public void handle(BufferedOutputStream responseStream) throws IOException {
                final var filePath = Path.of(fullPathTwo);
                final var mimeType = Files.probeContentType(filePath);
                final var template = Files.readString(filePath);
                final var content = template.replace(
                        "{time}",
                        LocalDateTime.now().toString()
                ).getBytes();
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + content.length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                responseStream.write(content);
                responseStream.flush();
            }
        });

        // Добавление 3-го handler (обработчика)
        myServer.addHandler(methodOne, fullPathThree, new Handler<BufferedOutputStream>() {
            public void handle(BufferedOutputStream responseStream) throws IOException {
                final var filePath = Path.of(fullPathThree);
                final var mimeType = Files.probeContentType(filePath);
                final var length = Files.size(filePath);
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                Files.copy(filePath, responseStream);
                responseStream.flush();
            }
        });

        // Добавление 4-го handler (обработчика)
        myServer.addHandler(methodOne, fullPathFour, new Handler<BufferedOutputStream>() {
            public void handle(BufferedOutputStream responseStream) throws IOException {
                final var filePath = Path.of(fullPathFour);
                final var mimeType = Files.probeContentType(filePath);
                final var length = Files.size(filePath);
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                Files.copy(filePath, responseStream);
                responseStream.flush();
            }
        });

        myServer.listen(PORT);
    }

}