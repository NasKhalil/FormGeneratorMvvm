package com.example.formgenerator.main.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.formgenerator.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileViewModel extends ViewModel {

    ProfileRepository profileRepository;

    public ProfileViewModel() {
        profileRepository = new ProfileRepository();
    }

    LiveData<String> getData(){
        return profileRepository.getData();
    }

    LiveData<User> displayUserData(String uid){
        return profileRepository.displayUserData(uid);
    }

    LiveData<FirebaseAuth> signOutUser() { return profileRepository.signOutUser() ;}

    LiveData<User> editUserData(String uid, String name, String lastName, String mail, String phone){ return profileRepository.editUserData(uid,  name, lastName, mail, phone); }
}