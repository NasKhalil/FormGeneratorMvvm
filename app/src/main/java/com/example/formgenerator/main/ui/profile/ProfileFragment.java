package com.example.formgenerator.main.ui.profile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.formgenerator.databinding.FragmentProfileBinding;
import com.example.formgenerator.inscription.InscriptionActivity;
import com.example.formgenerator.loadingDialog.LoadingDialog;
import com.example.formgenerator.login.LoginActivity;
import com.example.formgenerator.model.User;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUserDatum();
        binding.logout.setOnClickListener(v -> {
            signOutUser();
            startActivity(new Intent(requireContext(), LoginActivity.class));
            finishActivity();
        });

        binding.edit.setOnClickListener(v -> {
            binding.logout.setVisibility(View.GONE);
            binding.cancelBtn.setVisibility(View.VISIBLE);
            binding.edit.setVisibility(View.GONE);
            binding.save.setVisibility(View.VISIBLE);
            binding.email.setVisibility(View.GONE);
            binding.name.setVisibility(View.GONE);
            binding.phone.setVisibility(View.GONE);
            binding.textInputLayoutEmail.setVisibility(View.VISIBLE);
            binding.textInputLayoutName.setVisibility(View.VISIBLE);
            binding.textInputLayoutLastName.setVisibility(View.VISIBLE);
            binding.textInputLayoutPhone.setVisibility(View.VISIBLE);
        });


        binding.save.setOnClickListener(v -> {
            editUserProfile();
        });

        binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.logout.setVisibility(View.VISIBLE);
                binding.cancelBtn.setVisibility(View.GONE);
                binding.save.setVisibility(View.GONE);
                binding.edit.setVisibility(View.VISIBLE);
                binding.textInputLayoutEmail.setVisibility(View.GONE);
                binding.textInputLayoutName.setVisibility(View.GONE);
                binding.textInputLayoutLastName.setVisibility(View.GONE);
                binding.textInputLayoutPhone.setVisibility(View.GONE);
                binding.email.setVisibility(View.VISIBLE);
                binding.name.setVisibility(View.VISIBLE);
                binding.phone.setVisibility(View.VISIBLE);
            }
        });

    }

    private void getUserDatum() {

        profileViewModel
                .getData()
                .observe(getViewLifecycleOwner(), s -> profileViewModel
                        .displayUserData(s)
                        .observe(getViewLifecycleOwner(), user -> {
                            LoadingDialog loadingDialog = new LoadingDialog(requireContext());
                            loadingDialog.startLoadingDialog();
                        Glide.with(getActivity()).load(user.getUrl()).into(binding.profileImage);
                        binding.email.setText(user.getMail());
                        binding.name.setText(user.getName() + ' ' + user.getLastName());
                        binding.phone.setText(user.getPhone());

                        binding.textInputLayoutEmail.setHint(user.getMail());
                        binding.textInputLayoutName.setHint(user.getName());
                        binding.textInputLayoutLastName.setHint(user.getLastName());
                        binding.textInputLayoutPhone.setHint(user.getPhone());
                            loadingDialog.dismissDialog();
        }));
    }


    private void editUserProfile(){
        if (!isEmpty()) {
            binding.logout.setVisibility(View.VISIBLE);
            binding.cancelBtn.setVisibility(View.GONE);
            binding.save.setVisibility(View.GONE);
            binding.edit.setVisibility(View.VISIBLE);
            binding.textInputLayoutEmail.setVisibility(View.GONE);
            binding.textInputLayoutName.setVisibility(View.GONE);
            binding.textInputLayoutLastName.setVisibility(View.GONE);
            binding.textInputLayoutPhone.setVisibility(View.GONE);
            binding.email.setVisibility(View.VISIBLE);
            binding.name.setVisibility(View.VISIBLE);
            binding.phone.setVisibility(View.VISIBLE);

            String mail = binding.mailEdit.getText().toString();
            String name = binding.nameEdit.getText().toString();
            String lastName = binding.lastNameEdit.getText().toString();
            String phone = binding.phoneEdit.getText().toString();
            profileViewModel.getData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                    if (validEmail()) {
                        profileViewModel.editUserData(s, name, lastName, mail, phone)
                                .observe(getViewLifecycleOwner(),
                                        user -> Toast.makeText(requireContext(), "Edit Success", Toast.LENGTH_LONG).show());
                    }
                }
        });
        }
    }

    private void signOutUser(){
        profileViewModel.signOutUser().observe(getViewLifecycleOwner(), firebaseAuth -> firebaseAuth.signOut());
    }

    private void finishActivity() {
        if(getActivity() != null) {
            getActivity().finish();
        }
    }


    private boolean isEmpty() {
        boolean empty = false;

        if (TextUtils.isEmpty(binding.nameEdit.getText())) {
            empty = true;
            binding.nameEdit.setError("Empty Field");
        }
        if (TextUtils.isEmpty(binding.lastNameEdit.getText())) {
            empty = true;
            binding.lastNameEdit.setError("Empty Field");
        }
        if (TextUtils.isEmpty(binding.mailEdit.getText())) {
            empty = true;
            binding.mailEdit.setError("Empty Field");
        }
        if (TextUtils.isEmpty(binding.phoneEdit.getText())) {
            empty = true;
            binding.phoneEdit.setError("Empty Field");
        }

        return empty;
    }

    private boolean validEmail() {
        boolean valid = true;
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.mailEdit.getText()).matches()) {
            valid = false;
            binding.mailEdit.setError("Enter a valid email");
        }

        return valid;
    }

}