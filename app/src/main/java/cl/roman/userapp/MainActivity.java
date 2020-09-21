package cl.roman.userapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.roman.userapp.adapter.AdapterUser;
import cl.roman.userapp.model.User;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String URL="https://randomuser.me/api/?results=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

            List<User> list = new ArrayList<>();

            for(int i = 0; i<results.length();i++) {
                JSONObject user1 = results.getJSONObject(i);
                String email = user1.getString("email");
                String phone = user1.getString("phone");

                JSONObject name = user1.getJSONObject("name");
                String first = name.getString("first");
                String last = name.getString("last");

                JSONObject picture = user1.getJSONObject("picture");
                String thumbnail = picture.getString("large");

                User user = new User(first, last,phone,email,thumbnail);
                list.add(user);
            }

            //caargar el recycler
            RecyclerView rc = findViewById(R.id.rc_user);
            AdapterUser ad = new AdapterUser(this,list,R.layout.item_user);
            LinearLayoutManager lm = new LinearLayoutManager(this);
            lm.setOrientation(RecyclerView.VERTICAL);

            rc.setLayoutManager(lm);
            rc.setAdapter(ad);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}