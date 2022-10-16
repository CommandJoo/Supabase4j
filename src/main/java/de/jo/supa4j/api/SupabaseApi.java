package de.jo.supa4j.api;

import com.google.gson.GsonBuilder;
import de.jo.supa4j.http.RequestBuilder;
import de.jo.supa4j.http.util.MethodType;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.nio.charset.StandardCharsets;

public class SupabaseApi {

    private String url;
    private String route = "rest/v1";
    private String apikey;
    private boolean log = true;

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

    public SupabaseApi noLog() {
        this.log = false;
        return this;
    }

    public SupabaseResponse build(SupabaseRequest req) {
        try {
            if(this.log) {
                System.err.println("Request at Supabase URL: "+this.url);
                System.err.println("Request at Subroute: "+this.route);
                System.err.println("Request with Arguments: "+req.toRequest());
            }

            RequestBuilder builder = RequestBuilder.of(this.url+"/"+route+"/"+req.toRequest(), req.getType()).header("apikey", this.apikey).header("Authorization", "Bearer "+apikey);
            if(log) {
                System.err.println("Requesting to URL: "+(this.url+"/"+route+"/"+req.toRequest()));
            }
            builder.value(req.getPostValue());
            if(builder.getRequest() instanceof HttpPost || builder.getRequest() instanceof HttpPatch) {
                builder.header("Content-Type", "application/json");
                builder.header("Prefer", "return=representation");
            }

            HttpResponse response = builder.build();
            String s;
            if(response.getEntity() == null || response.getEntity().getContent() == null) {
                s = "{\"message\":\"Done, This is Either a Delete Request or Something might have gone wrong\"}";
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
