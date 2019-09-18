package com.wadektech.el_muzarae.utils;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FirebaseRealtimeDatabaseQueryLiveData<T> extends LiveData<List<T>> implements ValueEventListener {
    private final Class<T> type ;
    private DatabaseReference databaseReference ;
    private static final String TAG = "FirebaseRealtimeDB";
    private Context context ;

    public FirebaseRealtimeDatabaseQueryLiveData(Class<T> type, DatabaseReference databaseReference) {
        this.type = type;
        this.databaseReference = databaseReference;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.d(TAG, "onDataChange : dataSnapshot has been received");
        setValue(dataSnapshotToList(dataSnapshot));
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.d(TAG, "onCancelled : snapShot not received" + databaseError.getMessage());
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.d(TAG, "onActive : ValueEventListener has been added");
        databaseReference.addValueEventListener(this);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.d(TAG, "onActive : ValueEventListener has been removed");
        databaseReference.removeEventListener(this);
    }

    private List<T> dataSnapshotToList(DataSnapshot dataSnapshot) {
        final List<T> list = new ArrayList<>();
        if (!dataSnapshot.exists() || !dataSnapshot.hasChildren()){
            Log.d(TAG, "dataSnapshotToList : not dataSnapshot exists");
            return list ;
        } else {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                list.add(snapshot.getValue(type));
            }
        }

        return list ;
    }
}
