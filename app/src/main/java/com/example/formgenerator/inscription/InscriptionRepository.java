package com.example.formgenerator.inscription;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.example.formgenerator.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class InscriptionRepository {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrence;
    private FirebaseStorage mStorage;

    User mUser = new User();
    MutableLiveData<InscriptionResponse> setNewUser = new MutableLiveData<>();

    public void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRefrence = mDatabase.getReference("user");
        mStorage = FirebaseStorage.getInstance();
    }

    public MutableLiveData<InscriptionResponse> addUser(String name, String lastName, String mail, String pwd, String phone, Uri filePath) {
        initFirebase();

        mAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                mUser.setUid(task.getResult().getUser().getUid());
                mUser.setName(name);
                mUser.setLastName(lastName);
                mUser.setMail(mail);
                mUser.setPwd(pwd);
                mUser.setPhone(phone);
                mUser.setType(0);
                StorageReference child = mStorage.getReference().child(mUser.getUid());
                child.putFile(filePath).continueWithTask(task1 -> child.getDownloadUrl()).addOnCompleteListener(task2 -> {
                    if (task2.isSuccessful()) {
                        mUser.setUrl(task2.getResult().toString());
                        mRefrence.child(mUser.getUid()).setValue(mUser).addOnCompleteListener(task3 -> {
                            setNewUser.postValue(new InscriptionResponse(true, "new user added"));
                        });
                    }
                });
            } else {
                setNewUser.postValue(new InscriptionResponse(false, task.getException().getMessage()));
            }
        });
        return setNewUser;
    }


}

