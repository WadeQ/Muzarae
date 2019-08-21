package com.wadektech.el_muzarae.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Orders functionality coming soon :)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        materialDesignAnimatedDialog = NiftyDialogBuilder.getInstance(this);

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

        mAdminDashBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FarmerRegistrationFormActivity.class);
                finish();
                startActivity(intent);
            }
        });

        productsList = getAllProductsFromDB() ;

        productsAdapter = new ProductsAdapter(productsList, this, this) ;
        mRecycler.setAdapter(productsAdapter);

    }

    private List<Products> getAllProductsFromDB() {
        List<Products> products = new ArrayList<>();
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("Products") ;
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                   Products items = snapshot.getValue(Products.class);
                   products.add(items);
                }
                productsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database error, "
                        + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
       return products ;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        finish();
                        startActivity(intent);
                    }
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

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_bookmarks) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClicked(int position) {
      Intent intent = new Intent(MainActivity.this, FarmProductDetailActivity.class);
        ProductDetails productDetails = productDetailsList.get(position) ;
        intent.putExtra("profileImage",productDetails.getProfileImage());
        intent.putExtra("productImage", productDetails.getProductImage());
        intent.putExtra("price", productDetails.getSellingPrice());
        intent.putExtra("name", productDetails.getNameOfProduct());
        intent.putExtra("farmer", productDetails.getNameOfFarmer());
        intent.putExtra("phone", productDetails.getFarmerPhoneNumber());
        intent.putExtra("quantity", productDetails.getProductQuantity());
        intent.putExtra("county", productDetails.getFarmerCounty());
        intent.putExtra("state", productDetails.getFarmerState());
        intent.putExtra("desc", productDetails.getProductDescription());
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

}
