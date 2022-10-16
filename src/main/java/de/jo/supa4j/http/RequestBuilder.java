package de.jo.supa4j.http;

import de.jo.supa4j.http.util.Field;
import de.jo.supa4j.http.util.MethodType;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

public class RequestBuilder {

    private final HttpRequestBase request;
    private String custom;

    public RequestBuilder(String url, MethodType type) {
        switch (type) {
            case POST:
                this.request = new HttpPost(url);
                break;
            case UPDATE:
                this.request = new HttpPatch(url);
                break;
            case DELETE:
                this.request = new HttpDelete(url);
                break;
            default:
                this.request = new HttpGet(url);
        }
    }

    public RequestBuilder(HttpRequestBase base) {
        this.request = base;
    }

    public static RequestBuilder of(String url, MethodType type) {
        return new RequestBuilder(url, type);
    }

    public static RequestBuilder of(HttpRequestBase base) {
        return new RequestBuilder(base);
    }
    public RequestBuilder header(Field field) {
        this.request.addHeader(field.key, field.value);
        return this;
    }
    public RequestBuilder header(String key, Object value) {
        this.request.addHeader(key, value.toString());
        return this;
    }
    public RequestBuilder url(String url) {
       try {
           this.request.setURI(new URI(url));
           return this;
       } catch(Exception ignored) {
            return this;
       }
    }

    public RequestBuilder value(String value) {
        this.custom = value;
        return this;
    }


    public HttpRequestBase getRequest() {
        return this.request;
    }
    public String getCustom() {
        return custom;
    }

    public HttpResponse build() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        if(request instanceof HttpEntityEnclosingRequest) {
            HttpEntityEnclosingRequest post = ((HttpEntityEnclosingRequest) request);
            post.setEntity(new StringEntity(custom));
        }
        return client.execute(this.request);
    }

}
