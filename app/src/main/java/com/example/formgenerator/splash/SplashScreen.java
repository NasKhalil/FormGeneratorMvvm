package com.example.formgenerator.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.formgenerator.R;
import com.example.formgenerator.model.User;

public class SplashScreen extends AppCompatActivity {
    SplashScreenViewModel splashScreenViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreenViewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);

        isConnected();
    }

    private void isConnected (){

        splashScreenViewModel.getData().observe(SplashScreen.this, s -> {
            Log.e("TAG", "onCreate: "+s );
            if (!s.equals("disconected"))
            {
                splashScreenViewModel.getUser(s).observe(SplashScreen.this, user -> {

                });
            }
        });

    }
}