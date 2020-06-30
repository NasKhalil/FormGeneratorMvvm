package com.example.formgenerator.splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.formgenerator.FireBaseAuthRepository;
import com.example.formgenerator.model.User;

public class SplashScreenViewModel extends ViewModel {
    FireBaseAuthRepository fireBaseAuthRepository  ;
    public SplashScreenViewModel() {
        fireBaseAuthRepository = new FireBaseAuthRepository() ;
    }

    LiveData<String> getData (){
        return fireBaseAuthRepository.getData();
    }
    LiveData<User> getUser(String uid )
    {
        return fireBaseAuthRepository.getUserData(uid);
    }
}
