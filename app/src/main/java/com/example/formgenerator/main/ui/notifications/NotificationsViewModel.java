package com.example.formgenerator.main.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.formgenerator.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationsViewModel extends ViewModel {

    ProfileRepository profileRepository;

    public NotificationsViewModel() {
        profileRepository = new ProfileRepository();
    }

    LiveData<String> getData(){
        return profileRepository.getData();
    }

    LiveData<User> displayUserData(String uid){
        return profileRepository.displayUserData(uid);
    }
}