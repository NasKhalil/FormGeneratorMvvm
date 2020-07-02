package com.example.formgenerator.inscription;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.formgenerator.R;
import com.example.formgenerator.databinding.ActivityInscriptionBinding;
import com.example.formgenerator.main.MainActivity2;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class InscriptionActivity extends AppCompatActivity {

    ActivityInscriptionBinding binding;
    InscriptionViewModel inscriptionViewModel;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inscriptionViewModel = new ViewModelProvider(this).get(InscriptionViewModel.class);

        binding.addImg.setOnClickListener(v -> choosePicture(binding.getRoot()));

        binding.register.setOnClickListener(v -> registration());
    }

    private void registration() {
        if (!isEmpty()) {
            String name = binding.name.getText().toString();
            String lastName = binding.lastName.getText().toString();
            String mail = binding.mail.getText().toString();
            String pwd = binding.pwd.getText().toString();
            String phone = binding.phone.getText().toString();
            Uri fPath = filePath;

            if (validEmail() && validPassword() && valideImage()) {
                inscriptionViewModel.addUser(name, lastName, mail, pwd, phone, fPath).observe(InscriptionActivity.this, inscriptionResponse -> {
                    if (inscriptionResponse.isResult()) {
                        binding.register.setVisibility(View.GONE);
                        binding.progressBar.setVisibility(View.VISIBLE);
                        startActivity(new Intent(InscriptionActivity.this, MainActivity2.class));
                        finish();
                        Toast.makeText(InscriptionActivity.this, "Success", Toast.LENGTH_LONG).show();
                    } else {
                        binding.progressBar.setVisibility(View.GONE);
                        Snackbar.make(binding.getRoot(), inscriptionResponse.getMsg(), Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            getContentResolver().takePersistableUriPermission(filePath, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                binding.addImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void choosePicture(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private boolean valideImage() {
        boolean valid = true;
        if (filePath == null) {
            valid = false;
            Snackbar.make(binding.addImg, "Add image ", Snackbar.LENGTH_LONG);
        }
        return valid;
    }

    private boolean isEmpty() {
        boolean empty = false;

        if (TextUtils.isEmpty(binding.name.getText())) {
            empty = true;
            binding.name.setError("Empty Field");
        }
        if (TextUtils.isEmpty(binding.lastName.getText())) {
            empty = true;
            binding.lastName.setError("Empty Field");
        }
        if (TextUtils.isEmpty(binding.mail.getText())) {
            empty = true;
            binding.mail.setError("Empty Field");
        }
        if (TextUtils.isEmpty(binding.pwd.getText())) {
            empty = true;
            binding.pwd.setError("Empty Field");
        }
        if (TextUtils.isEmpty(binding.phone.getText())) {
            empty = true;
            binding.phone.setError("Empty Field");
        }

        return empty;
    }

    private boolean validEmail() {
        boolean valid = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.mail.getText()).matches()) {
            valid = false;
            binding.mail.setError("Enter a valid email");
        }

        return valid;
    }

    private boolean validPassword() {
        boolean valide = true;
        if (binding.pwd.getText().length() < 6) {
            valide = false;
            binding.pwd.setError("minimum 6 caracters ");
        }
        return valide;
    }


}