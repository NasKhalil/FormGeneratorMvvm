package com.example.formgenerator.main.ui.notifications;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.formgenerator.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileRepository {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrence;

    MutableLiveData<String> curentuser = new MutableLiveData<>();
    MutableLiveData<User> userData = new MutableLiveData<>();

    private void initFireBase() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
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
            curentuser.postValue("disconected");
        }

        return curentuser;
    }

    public MutableLiveData<User> displayUserData(String uid){
        mRefrence.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                userData.postValue(user);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return userData;
    }
}
