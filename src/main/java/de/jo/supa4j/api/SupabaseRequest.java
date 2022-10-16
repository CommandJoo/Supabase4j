package de.jo.supa4j.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.jo.supa4j.http.util.Field;
import de.jo.supa4j.http.util.MethodType;

import java.util.ArrayList;
import java.util.List;

public class SupabaseRequest {

    private String table;
    private String select;
    private final List<String> filters = new ArrayList<>();
    private MethodType type = MethodType.GET;
    private String postValue;

    public SupabaseRequest(String table) {
        this.table = table;
    }

    public SupabaseRequest() {
    }

    public SupabaseRequest table(String table) {
        this.table = table;
        return this;
    }

    public SupabaseRequest method(MethodType type) {
        this.type = type;
        return this;
    }
    public SupabaseRequest postValue(String value) {
        this.postValue = value;
        return this;
    }
    public SupabaseRequest postValue(Object value) {
        Gson gson = new GsonBuilder().create();
        this.postValue = gson.toJson(value);
        return this;
    }

    public SupabaseRequest filter(Field filter) {
        return filter(filter.key, filter.value);
    }
    public SupabaseRequest filter(String key, String value) {
        return filter(key + "=" + value);
    }
    public SupabaseRequest filter(String statement) {
        this.filters.add(statement);
        return this;
    }

    public SupabaseRequest select(String select) {
        this.select = select;
        return this;
    }

    public String toRequest() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.table);
        if(type != MethodType.POST) {
            sb.append(this.table.endsWith("?") ? "" : "?");
            for (int i = 0; i < filters.size(); i++) {
                if (i != 0) sb.append("&");
                sb.append(filters.get(i));
            }
        }
        if(type == MethodType.GET) {
            if(filters.size() > 0) {
                sb.append("&");
            }
            sb.append("select=");
            sb.append(this.select);
        }

        return sb.toString();
    }
    public MethodType getType() {
        return type;
    }
    public String getPostValue() {
        return postValue;
    }
}
