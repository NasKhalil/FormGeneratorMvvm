package com.example.formgenerator.main.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.formgenerator.adapter.MyAdapter;
import com.example.formgenerator.databinding.FragmentHomeBinding;
import com.example.formgenerator.model.Form;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    MyAdapter adapter;
    List<Form> myForms = new ArrayList<>();

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
             new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        adapter = new MyAdapter(myForms);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recycler.setAdapter(adapter);
        getData();

    }

    private void getData() {
        homeViewModel.displayForms().observe(getViewLifecycleOwner(), new Observer<List<Form>>() {
            @Override
            public void onChanged(List<Form> forms) {
                if (forms != null){
                    myForms.addAll(forms);
                }
                adapter.notifyDataSetChanged();
            }
        });


    }


}