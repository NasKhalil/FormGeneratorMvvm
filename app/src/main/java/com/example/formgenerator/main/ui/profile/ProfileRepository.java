package com.example.formgenerator.main.ui.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.formgenerator.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfileRepository {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrence;
    private FirebaseStorage mStorage;

    MutableLiveData<String> curentuser = new MutableLiveData<>();
    MutableLiveData<User> userData = new MutableLiveData<>();
    MutableLiveData<User> editUser = new MutableLiveData<>();
    MutableLiveData<FirebaseAuth> sign_out = new MutableLiveData<FirebaseAuth>();

    private void initFireBase() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRefrence = mDatabase.getReference("user");
        mStorage = FirebaseStorage.getInstance();
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

    public MutableLiveData<User> editUserData(String uid, String name, String lastName, String mail, String phone, String url){
                User user = new User();
                user.setName(name);
                user.setLastName(lastName);
                user.setMail(mail);
                user.setPhone(phone);
                user.setUrl(url);
                mRefrence.child(uid).setValue(user).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        editUser.postValue(user);
                    }

        });

        return userData;
    }

    public MutableLiveData<FirebaseAuth> signOutUser(){
        sign_out.postValue(mAuth);
        return sign_out;
    }


}
