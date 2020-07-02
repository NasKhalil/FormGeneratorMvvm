package com.example.formgenerator.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    Context context;

    public  SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveLoginDetails(String name, String email) {
        editor.putString("Name", name);
        editor.putString("Email", email);
        editor.commit();
    }

    public String getUserName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Name", "");
    }

    public void saveFormData(String pLunch, String pEvening, String checkOutArea, String nbOfCleaningStuff, String remarks, String customerProblems){
        SharedPreferences sharedPreferences = context.getSharedPreferences("FormDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /**
         * TODO: get form data
         */
    }
}
