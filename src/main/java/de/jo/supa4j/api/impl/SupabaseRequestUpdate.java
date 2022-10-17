package de.jo.supa4j.api.impl;

import de.jo.supa4j.api.SupabaseRequest;
import de.jo.supa4j.http.util.MethodType;

import java.util.ArrayList;
import java.util.List;

public class SupabaseRequestUpdate extends SupabaseRequest implements SupabasePostable<SupabaseRequestUpdate>, SupabaseFilterable<SupabaseRequestUpdate> {

    private String postValue;
    private List<String> filters;
    public SupabaseRequestUpdate(String table) {
        super(table);
        this.filters = new ArrayList<>();
    }
    public SupabaseRequestUpdate() {
    }

    @Override
    public String toRequest() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.table);
        sb.append(this.table.endsWith("?") ? "" : "?");
        for (int i = 0; i < filters.size(); i++) {
            if (i != 0) sb.append("&");
            sb.append(filters.get(i));
        }

        return sb.toString();
    }

    @Override
    public SupabaseRequestUpdate of(SupabaseRequest request) {
        return new SupabaseRequestUpdate(request.getTable());
    }

    @Override
    public SupabaseRequestUpdate postValue(String value) {
        this.postValue = value;
        return this;
    }
    @Override
    public String postValue() {
        return this.postValue;
    }

    @Override
    public List<String> getFilters() {
        return this.filters;
    }
    @Override
    public SupabaseRequestUpdate filter(String statement) {
        this.filters.add(statement);
        return this;
    }

    @Override
    public MethodType type() {
        return MethodType.UPDATE;
    }
}
