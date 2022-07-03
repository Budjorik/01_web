package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Request {
    private final String method;
    private final String path;
    private final String protocol;
    private final String body;

    public Request(String method, String path, String protocol, String body) {
        this.method = method;
        this.path = path;
        this.protocol = protocol;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getBody() {
        return body;
    }

    public Collection<String> getQueryParam(String name) {
        List<NameValuePair> params = URLEncodedUtils.parse(path, Charset.forName("UTF-8"));
        List<String> selectedParam = new ArrayList<>();
        for (NameValuePair param : params) {
            if (name.equals(param.getName())) {
                selectedParam.add(param.getValue());
            }
        }
        return selectedParam;
    }

    public Collection<NameValuePair> getQueryParams() throws URISyntaxException {
        List<NameValuePair> params = URLEncodedUtils.parse(new URI(path), StandardCharsets.UTF_8);
        return params;
    }

    public String getPathWithoutParams() {
        if (path.contains("?")) {
            String result = path.substring(0, path.indexOf('?'));
            return result;
        }
        return path;
    }

}