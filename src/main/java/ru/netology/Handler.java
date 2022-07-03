package ru.netology;

import java.io.IOException;

@FunctionalInterface
public interface Handler<T, R> {
    void handle(T t, R r) throws IOException;
}