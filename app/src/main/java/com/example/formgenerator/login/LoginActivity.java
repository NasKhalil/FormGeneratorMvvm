package com.example.formgenerator.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.formgenerator.MainActivity;
import com.example.formgenerator.R;
import com.example.formgenerator.databinding.ActivityLoginBinding;
import com.example.formgenerator.inscription.InscriptionActivity;
import com.example.formgenerator.main.MainActivity2;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;

import java.security.acl.Owner;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.button.setOnClickListener(v -> login());
    }

    private void login() {

        String mail = binding.mail.getText().toString();
        String pwd = binding.pwd.getText().toString();
       loginViewModel.getUserData(mail, pwd).observe(LoginActivity.this, result -> {
           if (result.isResult()) {
               LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity2.class));
               LoginActivity.this.finish();
           } else {
               Snackbar.make(binding.getRoot(), result.getMsg(), Snackbar.LENGTH_LONG).show();
           }

       });
    }

}