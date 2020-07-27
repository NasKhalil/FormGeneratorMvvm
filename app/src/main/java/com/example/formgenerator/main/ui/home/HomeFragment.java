package com.example.formgenerator.main.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.formgenerator.MainActivity;
import com.example.formgenerator.R;
import com.example.formgenerator.adapter.MyAdapter;
import com.example.formgenerator.databinding.FragmentHomeBinding;
import com.example.formgenerator.main.FormActivity;
import com.example.formgenerator.main.MainActivity2;
import com.example.formgenerator.model.Form;
import com.example.formgenerator.utils.SessionManager;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MyAdapter.onFormListener {
    SessionManager sessionManager;
    private final int x= 0;

    private FragmentHomeBinding binding;
    MyAdapter adapter;
    List<Form> myForms = new ArrayList<>();

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
             new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        adapter = new MyAdapter(myForms, this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.recycler.setAdapter(adapter);
        getData();
        binding.refresh.setOnRefreshListener(() -> {
            myForms.clear();
            getData();
        });

        binding.formAdd.setOnClickListener(v -> HomeFragment.this.showDialog(HomeFragment.this.getActivity()));

    }

    private void getData() {
        binding.refresh.setRefreshing(true);
        homeViewModel.displayForms().observe(getViewLifecycleOwner(), forms -> {
            myForms.clear();
            if (forms != null){
                myForms.addAll(forms);
            }
            adapter.notifyDataSetChanged();
            binding.refresh.setRefreshing(false);
        });
    }


    public void showDialog(Activity activity){
        sessionManager = new SessionManager(activity);
        final Dialog dialog = new Dialog(activity);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.form_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextInputEditText date = dialog.findViewById(R.id.date);
        String currentDate =  android.text.format.DateFormat.format("yyyy/MM/dd", new java.util.Date()).toString();
        date.setText(currentDate);

        TextInputEditText formTitle = dialog.findViewById(R.id.form_title);

        TextInputEditText user = dialog.findViewById(R.id.user);
        user.setText(sessionManager.getUserName());
        Log.d("name", sessionManager.getUserName());

        Button dialogBtn_cancel =  dialog.findViewById(R.id.btn_cancel);
        dialogBtn_cancel.setOnClickListener(v -> dialog.cancel());

        Button dialogBtn_okay = (Button) dialog.findViewById(R.id.btn_okay);
        dialogBtn_okay.setOnClickListener(v -> {

            String userText = user.getText().toString();
            String dateText = date.getText().toString();
            String f_title = formTitle.getText().toString();
            if (TextUtils.isEmpty(f_title)){
                formTitle.setError("field is empty");
            }
            else {
                homeViewModel.addNewForm(f_title, userText, dateText).observe(getViewLifecycleOwner(), form -> {
                    Toast.makeText(getContext(),"New Form added" ,Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                });
            }
        });

        dialog.show();
    }


    @Override
    public void onFormClick(int position) {
        Log.d("click", "onFormClick "+ position);
        Intent intent = new Intent(requireContext(), FormActivity.class);
        //intent.putExtra("form", )
        startActivity(intent);
    }
}