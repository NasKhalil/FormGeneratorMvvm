package com.example.formgenerator.main.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.formgenerator.model.Form;

import java.util.List;

public class HomeViewModel extends ViewModel {

    HomeRepository homeRepository;

    public HomeViewModel() {
       homeRepository = new HomeRepository();
    }

    LiveData<List<Form>> displayForms(){
        return homeRepository.getcForm();
    }
}