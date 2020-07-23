package com.example.formgenerator.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.formgenerator.R;
import com.example.formgenerator.databinding.ActivityFormBinding;

public class FormActivity extends AppCompatActivity {
    private ActivityFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(FormActivity.this);
            }
        });

    }

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        String[] utils = new String[] {"EditText", "Button", "RadioButton"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(FormActivity.this,
                        R.layout.drop_down_item,
                        R.id.drop,
                        utils);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.exposed_dropdown);
        autoCompleteTextView.setAdapter(adapter);

        Button dialogBtn_cancel =  dialog.findViewById(R.id.btn_cancel);
        dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        Button dialogBtn_okay = (Button) dialog.findViewById(R.id.btn_okay);
        dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Okay" ,Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }

}