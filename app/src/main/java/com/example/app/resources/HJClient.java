package com.example.app.resources;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by root on 3/26/14.
 */
public class HJClient {

    static InputStream is = null;
    static Gson gson = new Gson();
    DefaultHttpClient hClient = new DefaultHttpClient();

    public HJClient() {

    }

    public String get(String url) throws IOException {
        String result = "";
        HttpGet get = new HttpGet(url);
        HttpResponse response = hClient.execute(get);
        is = response.getEntity().getContent();
        if(is != null)
            result = convertInputStreamToString(is);
        else
            result = "Did not work!";
        return result;
    }

    // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    // check network connection
//    public boolean isConnected(){
//        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected())
//            return true;
//        else
//            return false;
//    }

}
