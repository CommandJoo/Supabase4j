package de.jo.supa4j.api.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.jo.supa4j.api.SupabaseRequest;

public interface SupabasePostable<T extends SupabaseRequest> {

    public T postValue(String value);
    public default T postValue(Object value) {
        return postValue(new GsonBuilder().create().toJson(value));
    }
    public String postValue();

}
