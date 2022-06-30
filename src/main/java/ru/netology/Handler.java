package ru.netology;

import java.io.IOException;

@FunctionalInterface
public interface Handler<R> {
    void handle(R r) throws IOException;
}