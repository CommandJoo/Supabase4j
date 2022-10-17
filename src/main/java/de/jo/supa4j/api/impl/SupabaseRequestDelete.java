package de.jo.supa4j.api.impl;

import de.jo.supa4j.api.SupabaseRequest;
import de.jo.supa4j.http.util.MethodType;

import java.util.ArrayList;
import java.util.List;

public class SupabaseRequestDelete extends SupabaseRequest implements SupabaseFilterable<SupabaseRequestDelete> {

    private List<String> filters;

    public SupabaseRequestDelete(String table) {
        super(table);
        this.filters = new ArrayList<>();
    }
    public SupabaseRequestDelete() {
    }

    @Override
    public List<String> getFilters() {
        return filters;
    }

    @Override
    public SupabaseRequestDelete filter(String statement) {
        this.filters.add(statement);
        return this;
    }

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
    public SupabaseRequestDelete of(SupabaseRequest request) {
        return new SupabaseRequestDelete(request.getTable());
    }

    @Override
    public MethodType type() {
        return MethodType.DELETE;
    }
}
