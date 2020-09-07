package cl.roman.userapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String URL="https://randomuser.me/api/?results=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processHttp();
    }


    public void processHttp(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String data = new String(responseBody);
                Log.d("INFO", data);
                processUsers(data);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void processUsers(String data) {
        try {
            JSONObject root = new JSONObject(data);
            JSONArray results = root.getJSONArray("results");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}