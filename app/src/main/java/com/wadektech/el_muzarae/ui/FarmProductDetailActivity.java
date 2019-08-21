package com.wadektech.el_muzarae.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.wadektech.el_muzarae.R;
import com.wadektech.el_muzarae.pojos.ProductDetails;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FarmProductDetailActivity extends AppCompatActivity {
    ImageButton mBackNavigation ;
    ImageView productImage ;
    CircleImageView profileImage ;
    TextView productPrice , nameOfProduct, nameOfFarmer,farmerPhoneNumber,productQuantity,
             farmerCounty, farmerState, productDescription ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_product_detail);

        getProductDetailsFromDB();

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

        mBackNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        Intent intent = getIntent() ;
        String profileImageUrl = intent.getStringExtra("profileImage");
        String productImageUrl = intent.getStringExtra("productImage");
        String price = intent.getStringExtra("price");
        String name = intent.getStringExtra("name");
        String farmer = intent.getStringExtra("farmer");
        String phone = intent.getStringExtra("phone");
        String quantity = intent.getStringExtra("quantity");
        String county = intent.getStringExtra("county");
        String state = intent.getStringExtra("state");
        String desc = intent.getStringExtra("desc");

        Picasso.with(this)
                .load(productImageUrl)
                .fit()
                .into(productImage);

        Picasso.with(this)
                .load(profileImageUrl)
                .fit()
                .into(profileImage);

        productPrice.setText(price);
        nameOfProduct.setText(name);
        nameOfFarmer.setText(farmer);
        farmerPhoneNumber.setText(phone);
        productQuantity.setText(quantity);
        farmerCounty.setText(county);
        farmerState.setText(state);
        productDescription.setText(desc);
    }


    private List<ProductDetails> getProductDetailsFromDB() {
        List<ProductDetails> details = new ArrayList<>();


        return details ;
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
