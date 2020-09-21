package cl.roman.userapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cl.roman.userapp.DetailActivity;
import cl.roman.userapp.R;
import cl.roman.userapp.model.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.UserHolder> {

    public Activity activity;
    public List<User> list;
    public int item_user;

    public AdapterUser(Activity activity, List<User> list, int item_user) {
        this.activity = activity;
        this.list = list;
        this.item_user = item_user;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item_user,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = list.get(position);
        holder.item_name.setText(user.name+" "+user.last);
        holder.item_email.setText(user.email);
        holder.item_phone.setText(user.phone);
        Glide.with(activity).load(user.thumbnail).into(holder.item_img);
        holder.urlImage = user.thumbnail;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        CircleImageView item_img;
        TextView item_name, item_phone, item_email;
        RelativeLayout item_card;

        String urlImage;

        public UserHolder( View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_img);
            item_name = itemView.findViewById(R.id.item_name);
            item_phone = itemView.findViewById(R.id.item_phone);
            item_email = itemView.findViewById(R.id.item_email);
            item_card = itemView.findViewById(R.id.item_card);

            item_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, DetailActivity.class);
                    intent.putExtra("NAME",item_name.getText().toString());
                    intent.putExtra("PICTURE", urlImage);
                    intent.putExtra("EMAIL",item_email.getText().toString());
                    intent.putExtra("PHONE",item_phone.getText().toString());
                    activity.startActivity(intent);
                    /*Toast.makeText(activity,
                            "Hello "+item_name.getText().toString(),
                            Toast.LENGTH_LONG).show();*/

                }
            });
            
            item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity,
                            "Hello "+item_name.getText().toString(),
                            Toast.LENGTH_LONG).show();
                }
            });

        }


    }


}
