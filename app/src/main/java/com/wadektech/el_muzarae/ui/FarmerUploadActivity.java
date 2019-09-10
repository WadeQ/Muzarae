package com.wadektech.el_muzarae.ui;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.wadektech.el_muzarae.R;

import java.util.HashMap;

public class FarmerUploadActivity extends AppCompatActivity {

    EditText productName, productQty, productPrice, productDesc ;
    ImageButton productImagePicker ;
    Button saveDetails ;
    private final static String TAG = "AdminActivityLog";
    private static final int PICK_IMAGE_REQUEST = 13 ;
    private Uri imageUri ;
    ProgressDialog mDialog ;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    FirebaseStorage firebaseStorage ;
    StorageReference storageReference ;
    ProgressBar mProgress;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_upload);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        productName = findViewById(R.id.et_product_name);
        productQty = findViewById(R.id.et_product_qty);
        productPrice = findViewById(R.id.et_product_price);
        productDesc = findViewById(R.id.et_product_desc);
        productImagePicker = findViewById(R.id.product_image_upload);
        saveDetails = findViewById(R.id.btn_product_details_save);

        mDialog = new ProgressDialog(FarmerUploadActivity.this);
        mProgress = new ProgressBar(FarmerUploadActivity.this) ;

        firebaseStorage = FirebaseStorage.getInstance() ;
        storageReference = firebaseStorage.getReference().child("Products");

        productImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromGallery();
            }
        });

        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProductDetailsToDB();
            }
        });
    }

    private void pickImageFromGallery() {
        Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageIntent.setType("image/*") ;
        imageIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(imageIntent.createChooser(imageIntent, "Choose image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            Log.d(TAG, "Returned " + data.getData()) ;
            imageUri = data.getData() ;
            //display image on the imageView
            Picasso.with(this)
                    .load(imageUri)
                    .fit()
                    .into(productImagePicker);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton() ;
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
    }

    private void uploadProductDetailsToDB(){
        String photoId = "";
        if (firebaseUser != null){
            photoId = firebaseUser.getUid();
        }
        if (imageUri != null){
            storageReference.child("." + getFileExtension(imageUri)) ;
            storageReference.child(photoId).putFile(imageUri).addOnSuccessListener(taskSnapshot ->
                    taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(uri -> {
                        String url = uri.toString();
                        String nameOfProduct = productName.getText().toString().trim();
                        String quantity = productQty.getText().toString().trim();
                        String price = productPrice.getText().toString().trim();
                        String description = productDesc.getText().toString().trim();

                        final ProgressDialog mDialog = new ProgressDialog(FarmerUploadActivity.this);
                        mDialog.setTitle("Saving Details");
                        mDialog.setMessage("Saving your details, please be patient...");
                        mDialog.show();

                        String userId = "";
                        if (firebaseUser != null) {
                            userId = firebaseUser.getUid();
                        }

                        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("Products");

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("url", url);
                        hashMap.put("name", nameOfProduct);
                        hashMap.put("quantity", quantity);
                        hashMap.put("price", price);
                        hashMap.put("description", description);

                        dRef.child(userId).updateChildren(hashMap).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                //we have a response, lets dismiss the dialog
                                mDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Items saved successfully..", Toast.LENGTH_SHORT).show();

                            } else if (task.getException() != null) {
                                Log.d(TAG, "Error saving message is " + task.getException().getMessage());
                                Toast.makeText(getApplicationContext(), "Items could not be saved, error "
                                        + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "Error is of type " + e.getMessage());
                            }
                        });
                    })).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(FarmerUploadActivity.this.getApplicationContext(), "Error of type " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) ;
        } else {
            Toast.makeText(getApplicationContext(), "No file has been selected!", Toast.LENGTH_SHORT).show();
        }
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
