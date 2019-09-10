package com.wadektech.el_muzarae.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wadektech.el_muzarae.R;
import com.wadektech.el_muzarae.adapter.ProductsAdapter;
import com.wadektech.el_muzarae.auth.FarmerRegistrationFormActivity;
import com.wadektech.el_muzarae.auth.SignUpActivity;
import com.wadektech.el_muzarae.pojos.ProductDetails;
import com.wadektech.el_muzarae.pojos.Products;
import com.wadektech.el_muzarae.viewmodels.ProductsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , ProductsAdapter.OnItemClickListener {
    RecyclerView mRecycler ;
    ProductsAdapter productsAdapter ;
    LinearLayoutManager linearLayoutManager ;
    List<Products> productsList ;
    List<ProductDetails> productDetailsList ;
    ImageView mAdminDashBoard ;
    NiftyDialogBuilder materialDesignAnimatedDialog;
    public static final String TAG = "Main Activity";
    DatabaseReference dRef ;
    public static final String INTENT_1 = "productImage";
    public static final String INTENT_2 = "profileImage";
    public static final String INTENT_3 = "price";
    public static final String INTENT_4 = "productName";
    public static final String INTENT_5 = "farmerName";
    public static final String INTENT_6 = "phone";
    public static final String INTENT_7 = "quantity";
    public static final String INTENT_8 = "county";
    public static final String INTENT_9 = "state";
    public static final String INTENT_10 = "desc";
    public ProductsViewModel productsViewModel ;
    //Context context = MainActivity.this ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        materialDesignAnimatedDialog = NiftyDialogBuilder.getInstance(this);

        FirebaseDatabase.getInstance().getReference().child("Products").keepSynced(true);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mAdminDashBoard = findViewById(R.id.admin_dashboard);
        mRecycler = findViewById(R.id.products_recycler);
        linearLayoutManager = new LinearLayoutManager(this) ;
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdminDashBoard.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FarmerRegistrationFormActivity.class);
            finish();
            startActivity(intent);
        });

        productDetailsList = getExtraItemsFromDB();

       // List<Products> productsList = new ArrayList<>();

        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        productsViewModel.getAllProducts().observe(this, products -> {

            productsAdapter = new ProductsAdapter(products, this, this) ;
            mRecycler.setAdapter(productsAdapter);

        });
    }

    private List<ProductDetails> getExtraItemsFromDB() {
        List<ProductDetails> details = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Products").keepSynced(true);
        dRef = FirebaseDatabase.getInstance().getReference().child("Products") ;
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                details.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProductDetails pDetails = snapshot.getValue(ProductDetails.class);
                    details.add(pDetails);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return details ;
    }
    //shifting to liveData, this method no longer holds.
    /**

    private List<Products> getAllProductsFromDB() {
        List<Products> products = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Products").keepSynced(true);
        dRef = FirebaseDatabase.getInstance().getReference().child("Products") ;
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Products items = snapshot.getValue(Products.class);
                    products.add(items);
                    productsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database error, "
                        + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return products ;
    }
**/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                finish();
                startActivity(intent);
            }, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.menuLogout){
            animatedDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_market) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.menuLogout) {
            animatedDialog();
        } else if (id == R.id.nav_share) {
            shareApp();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClicked(int position) {

        Intent intent = new Intent(MainActivity.this, FarmProductDetailActivity.class);
        ProductDetails productDetails1 = productDetailsList.get(position);
        intent.putExtra(INTENT_1,productDetails1.getUrl());
        intent.putExtra(INTENT_2, productDetails1.getUrl());
        intent.putExtra(INTENT_3, productDetails1.getPrice());
        intent.putExtra(INTENT_4, productDetails1.getName());
        intent.putExtra(INTENT_5, productDetails1.getNameOfFarmer());
        intent.putExtra(INTENT_6, productDetails1.getPhone());
        intent.putExtra(INTENT_7, productDetails1.getQuantity());
        intent.putExtra(INTENT_8, productDetails1.getCounty());
        intent.putExtra(INTENT_9, productDetails1.getState());
        intent.putExtra(INTENT_10, productDetails1.getDescription());
        startActivity(intent);

    }

    //implement a custom dialog for our logout functionality
    private void animatedDialog() {
        materialDesignAnimatedDialog
                .withTitle("Logout")
                .withMessage("Are you sure you want to log out of El Muzarae? Your session will be deleted.")
                .withDialogColor("#688ae2")
                .withButton1Text("OK")
                .isCancelableOnTouchOutside(true)
                .withButton2Text("Cancel")
                .withDuration(700)
                .withEffect(Effectstype.Fall)
                .setButton1Click(v -> logOut())
                .setButton2Click(v -> materialDesignAnimatedDialog.dismiss());
        materialDesignAnimatedDialog.show();
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //implement a custom dialog for share app functionality
    public void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey, enjoy el muzarae and share with friends and family");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
