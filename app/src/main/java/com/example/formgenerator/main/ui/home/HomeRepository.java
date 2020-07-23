package com.example.formgenerator.main.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.formgenerator.model.Form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class HomeRepository {
    Form mForm = new Form();

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrence;
    private FirebaseStorage mStorage;

    private void initFireBase() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRefrence = mDatabase.getReference("form");
        mStorage = FirebaseStorage.getInstance();
    }


    MutableLiveData<List<Form>> cForm = new MutableLiveData<>();
    MutableLiveData<Form> addForm = new MutableLiveData<>();



    public MutableLiveData<List<Form>> getcForm(){
        initFireBase();

        mRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Form f ;
                List<Form> list = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                     f = child.getValue(Form.class);
                    //  f.setId(Integer.parseInt(child.getKey()));
                    list.add(f);
                }
                cForm.postValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                cForm.postValue(null);
            }
        });
        return cForm;
    }

    public MutableLiveData<Form> addNewForm( String form_tittle, String creator, String insert_date){
        initFireBase();


        DatabaseReference push = mRefrence.push();
        push.setValue(mForm).addOnCompleteListener(task -> {
            mForm.setId(push.getKey());
            mForm.setForm_tittle(form_tittle);
            mForm.setCreator(creator);
            mForm.setInsert_date(insert_date);
            if (task.isSuccessful()){
                addForm.setValue(mForm);
            }

        });

        return addForm;
    }


}
