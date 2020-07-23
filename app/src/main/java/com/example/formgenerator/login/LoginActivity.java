package com.example.formgenerator.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.example.formgenerator.MainActivity;
import com.example.formgenerator.R;
import com.example.formgenerator.databinding.ActivityLoginBinding;
import com.example.formgenerator.inscription.InscriptionActivity;
import com.example.formgenerator.main.MainActivity2;
import com.example.formgenerator.model.User;
import com.example.formgenerator.utils.SessionManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;

import java.security.acl.Owner;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.login.setOnClickListener(v -> login());

        binding.register.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, InscriptionActivity.class)));
    }

    private void login() {
        if (!isEmpty()) {
            String mail = binding.mail.getText().toString();
            String pwd = binding.pwd.getText().toString();

            if (validEmail() && validPassword()) {
                loginViewModel.getUserData(mail, pwd).observe(LoginActivity.this, result -> {
                    loginViewModel.loginRepository.getUserData().observe(LoginActivity.this, new Observer<User>() {
                        @Override
                        public void onChanged(User user) {
                            // shared preferences save login details
                            sessionManager = new SessionManager(getApplicationContext());
                            sessionManager.saveLoginDetails(user.getName(), user.getMail());
                            binding.login.setVisibility(View.GONE);
                            binding.progressBarLogin.setVisibility(View.VISIBLE);
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                            LoginActivity.this.finish();
                        }
                    });
                });
            }
        }
    }

    private boolean isEmpty() {
        boolean empty = false;

        if (TextUtils.isEmpty(binding.mail.getText())) {
            empty = true;
            binding.mail.setError("Empty Field");
        }
        if ((TextUtils.isEmpty(binding.pwd.getText()))) {
            empty = true;
            binding.pwd.setError("Empty Field");
        }
        return empty;
    }

    private boolean validEmail() {
        boolean valid = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.mail.getText()).matches()) {
            valid = false;
            binding.mail.setError("Enter a valid Email");
        }

        return valid;
    }

    private boolean validPassword() {
        boolean valide = true;
        if (binding.pwd.getText().length() < 6) {
            valide = false;
            binding.pwd.setError("6 caracters minimun ");
        }
        return valide;
    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("EXIT")
                .setMessage("Are you sure you want to close application")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show();
    }
}