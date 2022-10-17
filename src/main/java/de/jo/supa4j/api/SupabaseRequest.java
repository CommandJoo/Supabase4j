package de.jo.supa4j.api;

import de.jo.supa4j.api.impl.SupabaseRequestDelete;
import de.jo.supa4j.api.impl.SupabaseRequestGet;
import de.jo.supa4j.api.impl.SupabaseRequestPost;
import de.jo.supa4j.api.impl.SupabaseRequestUpdate;
import de.jo.supa4j.http.util.Field;
import de.jo.supa4j.http.util.MethodType;

import java.util.ArrayList;
import java.util.List;

public abstract class SupabaseRequest {

    protected String table;

    public SupabaseRequest(String table) {
        this.table = table;
    }
    public SupabaseRequest() {
    }

    public static SupabaseRequest create(String table) {
        return new SupabaseRequest(table) {
            @Override
            public String toRequest() {
                return "Invalid Request";
            }

            @Override
            public <T extends SupabaseRequest> T of(SupabaseRequest request) {
                return null;
            }

            @Override
            public MethodType type() {
                return MethodType.GET;
            }
        };
    }

    public static SupabaseRequest create() {
        return new SupabaseRequest() {
            @Override
            public String toRequest() {
                return "Invalid Request";
            }

            @Override
            public <T extends SupabaseRequest> T of(SupabaseRequest request) {
                return null;
            }

            @Override
            public MethodType type() {
                return MethodType.GET;
            }
        };
    }

    public SupabaseRequest table(String table) {
        this.table = table;
        return this;
    }

    public <T extends SupabaseRequest>T method(MethodType type) {
        switch (type) {
            case POST:
                return (T) new SupabaseRequestPost().of(this);
            case UPDATE:
                return (T) new SupabaseRequestUpdate().of(this);
            case DELETE:
                return (T) new SupabaseRequestDelete().of(this);
            default:
                return (T) new SupabaseRequestGet().of(this);
        }
    }

    public abstract String toRequest();
    public String getTable() {
        return table;
    }

    public abstract <T extends SupabaseRequest>T of(SupabaseRequest request);
    public abstract MethodType type();

}
