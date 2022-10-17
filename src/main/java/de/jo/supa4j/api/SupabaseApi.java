package de.jo.supa4j.api;

import de.jo.supa4j.Debugger;
import de.jo.supa4j.api.impl.SupabasePostable;
import de.jo.supa4j.http.RequestBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;

import java.nio.charset.StandardCharsets;

public class SupabaseApi {

    private String url;
    private String route = "rest/v1";
    private String apikey;

    public SupabaseApi(String url, String apikey) {
        this.url = url;
        this.apikey = apikey;
    }
    public SupabaseApi(){
    }

    public SupabaseApi url(String url) {
        this.url = url;
        return this;
    }

    public SupabaseApi apikey(String apikey) {
        this.apikey = apikey;
        return this;
    }

    public SupabaseApi route(String route) {
        this.route = route;
        return this;
    }

    public SupabaseApi log(boolean b) {
        Debugger.log(b);
        return this;
    }

    public SupabaseResponse build(SupabaseRequest req) {
        try {
            Debugger.debug("Request at Supabase URL: " + this.url, Debugger.DebugType.INFO);
            Debugger.debug("Request at Subroute: " + this.route, Debugger.DebugType.INFO);
            Debugger.debug("Request with Arguments: " + req.toRequest(), Debugger.DebugType.INFO);

            RequestBuilder builder = RequestBuilder.of(this.url + "/" + route + "/" + req.toRequest(), req.type()).header("apikey", this.apikey).header("Authorization", "Bearer " + apikey);
            Debugger.debug("Requesting to URL: " + (this.url + "/" + route + "/" + req.toRequest()), Debugger.DebugType.INFO);


            if(req instanceof SupabasePostable) {
                builder = builder.value(((SupabasePostable<?>) req).postValue());
                builder = builder.header("Content-Type", "application/json");
                builder = builder.header("Prefer", "return=representation");

            }

            HttpResponse response = builder.build();
            String s;
            if(response.getEntity() == null || response.getEntity().getContent() == null) {
                s = "{\"message\":\"Done, This is Either a Delete Request or Something might have gone wrong\"}";
                Debugger.debug(s, Debugger.DebugType.WARNING);
            }else{
                s = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            }
            return new SupabaseResponse(s);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
