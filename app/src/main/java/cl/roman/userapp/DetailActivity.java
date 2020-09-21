package cl.roman.userapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    String name, phone, picture, email;
    TextView txt_name, txt_phone, txt_email;
    ImageView img;

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
        txt_name = findViewById(R.id.detail_name);
        txt_email = findViewById(R.id.detail_email);
        txt_phone = findViewById(R.id.detail_phone);
        img = findViewById(R.id.detail_img);

        txt_name.setText(name);
        txt_email.setText(email);
        txt_phone.setText(phone);

        Glide.with(this).load(picture).into(img);


        Log.d("INFO", name+","+phone+","+email+","+picture);

    }
}