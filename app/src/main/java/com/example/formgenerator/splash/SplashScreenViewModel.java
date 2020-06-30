package com.example.formgenerator.splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.formgenerator.model.User;

public class SplashScreenViewModel extends ViewModel {
    SplashScreenRepository splashScreenRepository;
    public SplashScreenViewModel() {
        splashScreenRepository = new SplashScreenRepository() ;
    }

    LiveData<String> getData (){
        return splashScreenRepository.getData();
    }
    LiveData<User> getUser(String uid )
    {
        return splashScreenRepository.getUserData(uid);
    }
}
