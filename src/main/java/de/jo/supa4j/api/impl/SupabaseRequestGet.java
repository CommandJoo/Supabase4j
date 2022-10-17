package de.jo.supa4j.api.impl;

import de.jo.supa4j.api.SupabaseRequest;
import de.jo.supa4j.http.util.MethodType;

import java.util.ArrayList;
import java.util.List;

public class SupabaseRequestGet extends SupabaseRequest implements SupabaseFilterable<SupabaseRequestGet> {

    private List<String> filters;
    private String select;

    public SupabaseRequestGet(String table) {
        super(table);
        this.filters = new ArrayList<>();
    }
    public SupabaseRequestGet() {
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
        if (filters.size() > 0) {
            sb.append("&");
        }
        sb.append("select=");
        sb.append(this.select);

        return sb.toString();
    }

    @Override
    public SupabaseRequestGet of(SupabaseRequest request) {
        return new SupabaseRequestGet(request.getTable());
    }

    @Override
    public MethodType type() {
        return MethodType.GET;
    }

    @Override
    public List<String> getFilters() {
        return filters;
    }

    @Override
    public SupabaseRequestGet filter(String statement) {
        filters.add(statement);
        return this;
    }

    public SupabaseRequestGet select(String select) {
        this.select = select;
        return this;
    }

    public String select() {
        return this.select;
    }
}
