package com.wadektech.el_muzarae.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wadektech.el_muzarae.R;
import com.wadektech.el_muzarae.ui.FarmerUploadActivity;
import com.wadektech.el_muzarae.ui.MainActivity;
import java.util.HashMap;
import de.hdodenhof.circleimageview.CircleImageView;

public class FarmerRegistrationFormActivity extends AppCompatActivity {
    EditText names, phoneNumber, fRegion, fState, fCounty, fEmail, fPassword ;
    Button btnRegisterFarmer ;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser ;
    CircleImageView profileImage ;
    //FirebaseUser firebaseUser ;
    DatabaseReference databaseReference ;
    public static final String TAG = "LoginActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_registration_form);

        names = findViewById(R.id.et_full_names);
        phoneNumber = findViewById(R.id.et_phone_number);
        fRegion = findViewById(R.id.et_region);
        fState = findViewById(R.id.et_state);
        fCounty = findViewById(R.id.et_county);
        fEmail = findViewById(R.id.et_farmer_email);
        fPassword = findViewById(R.id.et_confirm_farmer_password);
        profileImage = findViewById(R.id.profile_image);
        btnRegisterFarmer = findViewById(R.id.btn_farmer_register);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        btnRegisterFarmer.setOnClickListener(view -> {
            String email = fEmail.getText().toString().trim();
            String password = fPassword.getText().toString().trim();

           if (TextUtils.isEmpty(password) && email.length() > 8){
               fPassword.setError("Blank password or password less than 8 characters!");
           } else if (TextUtils.isEmpty(email)){
               fEmail.setError("Field cannot be blank!");
           } else {
               registerFarmer(email, password);
           }
        });
    }

            private void registerFarmer(String fEmail, String fPassword) {
                ProgressDialog mDialog = new ProgressDialog(FarmerRegistrationFormActivity.this);
                mDialog.setTitle("Registering Farmer");
                mDialog.setMessage("Please be patient as we register you...");
                mDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(fEmail, fPassword).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = "";
                        if (firebaseUser != null) {
                            userId = firebaseUser.getUid();
                        }

                        String nameOfFarmer = names.getText().toString().trim();
                        String phone = phoneNumber.getText().toString().trim();
                        String region = fRegion.getText().toString().trim();
                        String state = fState.getText().toString().trim();
                        String county = fCounty.getText().toString().trim();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference("Products").child(userId);

                        HashMap<String, Object> farmer = new HashMap<>();
                        farmer.put("email" ,fEmail);
                        farmer.put("nameOfFarmer" ,nameOfFarmer);
                        farmer.put("phone" ,phone);
                        farmer.put("region" ,region);
                        farmer.put("state" ,state);
                        farmer.put("county" ,county);

                        databaseReference.updateChildren(farmer).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                mDialog.dismiss();
                                Intent intent = new Intent(FarmerRegistrationFormActivity.this, FarmerUploadActivity.class);
                                finish();
                                startActivity(intent);
                            } else {
                                if (task1.getException() != null) {
                                    Log.d(TAG, "Error " + task1.getException().toString());
                                }
                            }

                        }).addOnFailureListener(e -> {
                            Toast.makeText(getApplicationContext(), "Error registering user " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        });

                    }
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
        }, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseUser != null){

        }
    }
}
