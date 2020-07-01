package com.example.formgenerator.inscription;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.net.URI;

public class InscriptionViewModel extends ViewModel {
    InscriptionRepository inscriptionRepository;

    public InscriptionViewModel() {
      inscriptionRepository = new InscriptionRepository();
    }

    LiveData<InscriptionResponse> addUser(String name, String lastName, String mail, String pwd, String phone, Uri filePath){
        return inscriptionRepository.addUser(  name,  lastName,  mail,  pwd,  phone,  filePath );
    }
}
