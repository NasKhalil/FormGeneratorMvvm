package com.example.formgenerator.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.formgenerator.model.User;
import com.example.formgenerator.utils.Const;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginRepository {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrence;
    MutableLiveData<LoginResponse> userData = new MutableLiveData<>();
    MutableLiveData<User> userDetails = new MutableLiveData<>();

    public void initFirebase(){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRefrence = mDatabase.getReference();
    }


    public MutableLiveData<LoginResponse> loginOperation(String mail, String pwd){
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

    public MutableLiveData<User> getUserData(){
        mRefrence.child(Const.USER_COLLECTION).child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User value = snapshot.getValue(User.class);
                userDetails.postValue(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                userDetails.postValue(null);
            }
        });
        return userDetails;
    }

}


