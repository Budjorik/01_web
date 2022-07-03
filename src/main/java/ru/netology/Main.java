package ru.netology;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        final int PORT = 9999; // Задаем номер порта http-сервера для подключения клиентов
        final String GET = "GET";
        final String FULL_PATH_ONE = "./public/spring.svg";
        final String FULL_PATH_TWO = "./public/classic.html";
        final String FULL_PATH_THREE = "./public/index.html";
        final String FULL_PATH_FOUR = "./public/spring.png";
        Server myServer = new Server();

        // Добавление 1-го handler (обработчика)
        myServer.addHandler(GET, FULL_PATH_ONE, new Handler<Request, BufferedOutputStream>() {
            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
                final var filePath = Path.of(FULL_PATH_ONE);
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
        myServer.addHandler(GET, FULL_PATH_TWO, new Handler<Request, BufferedOutputStream>() {
            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
                final var filePath = Path.of(FULL_PATH_TWO);
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
        myServer.addHandler(GET, FULL_PATH_THREE, new Handler<Request, BufferedOutputStream>() {
            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
                final var filePath = Path.of(FULL_PATH_THREE);
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
        myServer.addHandler(GET, FULL_PATH_FOUR, new Handler<Request, BufferedOutputStream>() {
            public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
                final var filePath = Path.of(FULL_PATH_FOUR);
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