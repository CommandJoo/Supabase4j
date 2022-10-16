# **Supa for Java**
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
## Description
Supa4J is a simple and easy to use API for ```java``` made to access Supabase-Tables, lets
you **delete**, **post**, And **update** data.

## Usage
First, you need to get an instance of the API
````java
SupabaseApi api = new SupabaseApi()
            .url("https://yoursupabase.supabase.co")
            .apikey("SUPABASE-KEY")
            .noLog(); //<- If you do not want requests to be logged to console
````

Once you have done that you're going to configure your request
````java
SupabaseRequest request = new SupabaseRequest("players")
                .select("*")
                .method(MethodType.GET)
                .filter("name=eq.Tim")
````

Finally to get the return value you have to actually send the request
````java
SupabaseResponse response = api.build(request);
````