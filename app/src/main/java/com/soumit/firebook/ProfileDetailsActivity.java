package com.soumit.firebook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.soumit.firebook.model.SingleInfo;

public class ProfileDetailsActivity extends BaseActivity {

    private static final String TAG = "ProfileDetailsActivity";
    private Context context;
    private ImageView circleImage;
    private TextView name;
    private TextView batch, address, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        context = ProfileDetailsActivity.this;

        initUiComponents();

        SingleInfo info = getIntent().getExtras().getParcelable("ProfileDataParcel");

        Glide.with(context).load(info.getImageLink()).into(circleImage);
        name.setText(info.getName());
        batch.setText(info.getBatch());
        address.setText(info.getAddress());
        email.setText(info.getEmail());
        phone.setText(info.getPhoneNumber());

        Toast.makeText(context, "Singleinfo clicked --> " + info.toString(), Toast.LENGTH_SHORT).show();
    }

    private void initUiComponents() {
        circleImage = findViewById(R.id.profile_image);
        name = findViewById(R.id.tv_name);
        batch = findViewById(R.id.tv_batch);
        address = findViewById(R.id.tv_address);
        email = findViewById(R.id.tv_email);
        phone = findViewById(R.id.tv_phoneNumber);
    }


}
