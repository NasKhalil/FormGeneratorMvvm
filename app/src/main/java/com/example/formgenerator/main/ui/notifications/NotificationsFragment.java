package com.example.formgenerator.main.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.formgenerator.R;
import com.example.formgenerator.databinding.FragmentNotificationsBinding;
import com.example.formgenerator.model.User;

public class NotificationsFragment extends Fragment {

    FragmentNotificationsBinding binding;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUserDatum();

    }

    private void getUserDatum() {
        notificationsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                notificationsViewModel.displayUserData(s).observe(getViewLifecycleOwner(), new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        Glide.with(getActivity()).load(user.getUrl()).into(binding.profileImage);
                        binding.email.setText(user.getMail());
                        binding.name.setText(user.getName() + ' ' + user.getLastName());
                        binding.phone.setText(user.getPhone());
                    }
                });
            }
        });
    }


}