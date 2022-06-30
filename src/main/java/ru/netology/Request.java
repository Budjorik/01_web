package ru.netology;

public class Request {
    private String method;
    private String header;
    private String protocol;
    private String body;

    public Request(String method, String header, String protocol, String body) {
        this.method = method;
        this.protocol = protocol;
        this.header = header;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getHeader() {
        return header;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getBody() {
        return body;
    }

}