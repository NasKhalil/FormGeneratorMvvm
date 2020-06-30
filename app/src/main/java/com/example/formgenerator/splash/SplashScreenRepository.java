package com.example.formgenerator.splash;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.formgenerator.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreenRepository {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrence;
    MutableLiveData<String> curentuser = new MutableLiveData<>();
    MutableLiveData<User> userData = new MutableLiveData<>();
    private void initFireBase() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        // le curseur
        mRefrence = mDatabase.getReference("user");

    }

    public MutableLiveData<String> getData (){
        initFireBase();
        if (mAuth.getCurrentUser()!=null)
        {
            curentuser.postValue(mAuth.getCurrentUser().getUid());
        }
        else
        {
            curentuser.postValue("disconected ");
        }

        return curentuser ;
    }

    public MutableLiveData<User> getUserData (String uid){

     mRefrence.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             User user = snapshot.getValue(User.class);

             userData.postValue(user);

         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {
             userData.postValue(new User());

         }
     });
        return userData ;
    }
}
