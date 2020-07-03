package com.example.formgenerator.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.formgenerator.MainActivity;
import com.example.formgenerator.R;
import com.example.formgenerator.databinding.ActivitySplashScreenBinding;
import com.example.formgenerator.login.LoginActivity;
import com.example.formgenerator.main.MainActivity2;
import com.example.formgenerator.model.User;

public class SplashScreen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    SplashScreenViewModel splashScreenViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        splashScreenViewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);

        binding.motionLayoutSC.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                isConnected();
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

            }
        });

        binding.motionLayoutSC.startLayoutAnimation();

    }

    private void isConnected (){

        splashScreenViewModel.getData().observe(SplashScreen.this, s -> {
            Log.e("TAG", "onCreate: "+s );
            if (!s.equals("disconected"))
            {
                splashScreenViewModel.getUser(s).observe(SplashScreen.this, user -> {
                    if (user.getType() == 1){
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    }else
                    {
                        startActivity(new Intent(SplashScreen.this, MainActivity2.class));
                    }
                    finish();
                });
            }
            else {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }
        });

    }
}