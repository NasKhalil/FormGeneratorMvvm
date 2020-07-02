package com.example.formgenerator.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginRepository {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrence;
    MutableLiveData<LoginResponse> userData = new MutableLiveData<>();

    public void initFirebase(){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRefrence = mDatabase.getReference();
    }


    public MutableLiveData<LoginResponse> getUserData(String mail, String pwd){
       initFirebase();
       mAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(task -> {
           if (task.isSuccessful()){
               userData.postValue(new LoginResponse(true, "Login Success"));
           }else {
               userData.postValue(new LoginResponse(false, task.getException().getMessage()));
           }
       });

        return userData;
    }

}


