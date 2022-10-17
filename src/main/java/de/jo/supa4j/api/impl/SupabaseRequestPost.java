package de.jo.supa4j.api.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.jo.supa4j.api.SupabaseRequest;
import de.jo.supa4j.http.util.MethodType;

public class SupabaseRequestPost extends SupabaseRequest implements SupabasePostable<SupabaseRequestPost>{

    protected String postValue;

    public SupabaseRequestPost(String table) {
        super(table);
    }

    public SupabaseRequestPost() {
    }

    @Override
    public SupabaseRequestPost postValue(String value) {
        this.postValue = value;
        return this;
    }
    @Override
    public String postValue() {
        return this.postValue;
    }


    public String toRequest() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.table);
        return sb.toString();
    }

    @Override
    public SupabaseRequestPost of(SupabaseRequest request) {
        return new SupabaseRequestPost(request.getTable());
    }

    @Override
    public MethodType type() {
        return MethodType.POST;
    }
}
