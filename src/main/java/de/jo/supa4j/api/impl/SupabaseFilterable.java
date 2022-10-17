package de.jo.supa4j.api.impl;

import de.jo.supa4j.api.SupabaseRequest;
import de.jo.supa4j.http.util.Field;

import java.util.List;

public interface SupabaseFilterable<T extends SupabaseRequest> {

    public List<String> getFilters();
    public default T filter(Field filter) {
        return filter(filter.key, filter.value);
    }
    public default T filter(String key, String value) {
        return filter(key + "=" + value);
    }
    public T filter(String statement);
}
