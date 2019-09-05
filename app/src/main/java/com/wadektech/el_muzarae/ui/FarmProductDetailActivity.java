package com.wadektech.el_muzarae.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wadektech.el_muzarae.R;
import com.wadektech.el_muzarae.pojos.ProductDetails;
import com.wadektech.el_muzarae.utils.Constants;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_1;
import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_10;
import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_2;
import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_3;
import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_4;
import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_5;
import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_6;
import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_7;
import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_8;
import static com.wadektech.el_muzarae.ui.MainActivity.INTENT_9;

public class FarmProductDetailActivity extends AppCompatActivity {
    ImageButton mBackNavigation;
    ImageView productImage;
    CircleImageView profileImage;
    TextView productPrice, nameOfProduct, nameOfFarmer, farmerPhoneNumber, productQuantity,
            farmerCounty, farmerState, productDescription;
    Context context;
    Button callOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_product_detail);

        productImage = findViewById(R.id.product_image);
        productPrice = findViewById(R.id.tv_product_price);
        nameOfProduct = findViewById(R.id.tv_product_name);
        nameOfFarmer = findViewById(R.id.tv_farmer_name);
        farmerPhoneNumber = findViewById(R.id.tv_farmer_number);
        productQuantity = findViewById(R.id.tv_product_qty);
        farmerCounty = findViewById(R.id.tv_farm_county);
        farmerState = findViewById(R.id.tv_farmer_state);
        productDescription = findViewById(R.id.tv_product_desc);
        profileImage = findViewById(R.id.profile_image);
        mBackNavigation = findViewById(R.id.product_back);
        callOrderBtn = findViewById(R.id.btn_call_to_order);

        mBackNavigation.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(intent);
        });

        Intent intent = getIntent();
        String productImageUrl = intent.getStringExtra(INTENT_1);
        String profileImageUrl = intent.getStringExtra(INTENT_2);
        String price = intent.getStringExtra(INTENT_3);
        String name = intent.getStringExtra(INTENT_4);
        String farmer = intent.getStringExtra(INTENT_5);
        String phone = intent.getStringExtra(INTENT_6);
        String quantity = intent.getStringExtra(INTENT_7);
        String county = intent.getStringExtra(INTENT_8);
        String state = intent.getStringExtra(INTENT_9);
        String desc = intent.getStringExtra(INTENT_10);

        Picasso.with(context)
                .load(productImageUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(productImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context)
                                .load(productImageUrl)
                                //using a default image when images are not loaded
                                .error(R.drawable.farmers)
                                .into(productImage);
                    }
                });

        Picasso.with(context)
                .load(profileImageUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(profileImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context)
                                .load(profileImageUrl)
                                //using a default image when images are not loaded
                                .error(R.drawable.farmers)
                                .into(profileImage);
                    }
                });

        productPrice.setText(price);
        nameOfProduct.setText(name);
        nameOfFarmer.setText(farmer);
        farmerPhoneNumber.setText(phone);
        productQuantity.setText(quantity);
        farmerCounty.setText(county);
        farmerState.setText(state);
        productDescription.setText(desc);

        callOrderBtn.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + phone;
            i.setData(Uri.parse(p));
            startActivity(i);
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        },0);
    }
}
