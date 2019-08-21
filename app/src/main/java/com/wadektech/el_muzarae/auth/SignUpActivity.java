package com.wadektech.el_muzarae.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wadektech.el_muzarae.R;
import com.wadektech.el_muzarae.pojos.User;
import com.wadektech.el_muzarae.ui.MainActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText mUsername, mPassword, mEmail ;
    Button mLogin ;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser ;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    public static final String TAG = "LoginActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password) ;
        mEmail = findViewById(R.id.et_email);
        mLogin = findViewById(R.id.btn_login) ;

        firebaseAuth = FirebaseAuth.getInstance() ;
        firebaseUser = firebaseAuth.getCurrentUser() ;

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString().trim() ;
                String password = mPassword.getText().toString().trim() ;
                String email = mEmail.getText().toString().trim() ;

                if (TextUtils.isEmpty(username)){
                    mUsername.setError("Field cannot be blank!");
                } else if (TextUtils.isEmpty(password) && password.length() >= 8) {
                    mPassword.setError("Password cannot be less than 8 characters");
                } else if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Field cannot be blank!");
                } else {
                    ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                    mDialog.setTitle("Registering User");
                    mDialog.setMessage("Please be patient as we log you in...");
                    mDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String userId = "";
                            if (firebaseUser != null) {
                                userId = firebaseUser.getUid();
                            }

                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

                            User user = new User(username,email);

                            databaseReference.setValue(user).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    mDialog.dismiss();
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
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

                        } else {
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Something went wrong" +task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseUser != null){
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    }
}
