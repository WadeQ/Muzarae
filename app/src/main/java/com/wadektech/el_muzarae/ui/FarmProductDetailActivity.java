package com.wadektech.el_muzarae.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wadektech.el_muzarae.R;
import com.wadektech.el_muzarae.database.ProductDetails;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.wadektech.el_muzarae.ui.MainActivity.PRODUCT_DETAILS;

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
        ProductDetails productDetails = intent.getParcelableExtra(PRODUCT_DETAILS);
        String productImageUrl = productDetails.getUrl();
        String profileImageUrl = productDetails.getUrl();
        String price = productDetails.getPrice();
        String name = productDetails.getNameOfProduct();
        String farmer = productDetails.getNameOfFarmer();
        String phone = productDetails.getPhone();
        String quantity = productDetails.getQuantity();
        String county = productDetails.getCounty();
        String state = productDetails.getState();
        String desc = productDetails.getDescription();

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
