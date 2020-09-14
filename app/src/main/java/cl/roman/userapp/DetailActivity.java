package cl.roman.userapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    String name, phone, picture, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //initialize values from RecyclerView
        name = getIntent().getStringExtra("NAME");
        picture = getIntent().getStringExtra("PICTURE");
        phone = getIntent().getStringExtra("PHONE");
        email = getIntent().getStringExtra("EMAIL");
        //cargar en los controles del detail activity la info de arriba

        Log.d("INFO", name+","+phone+","+email+","+picture);

    }
}