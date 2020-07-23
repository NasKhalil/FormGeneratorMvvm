package com.example.formgenerator.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


public class LoginViewModel extends ViewModel {

    LoginRepository loginRepository;

    public LoginViewModel() {  loginRepository = new LoginRepository(); }



    LiveData<LoginResponse> getUserData(String mail, String pwd) {
        return loginRepository.loginOperation(mail, pwd);
    }
}
