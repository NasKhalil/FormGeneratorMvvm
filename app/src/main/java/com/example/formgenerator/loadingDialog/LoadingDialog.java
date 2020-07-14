package com.example.formgenerator.loadingDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.example.formgenerator.R;

public class LoadingDialog {

    Context context;
    Dialog dialog;

   public LoadingDialog(Context mContext){
        context = mContext;
    }

    public void startLoadingDialog(){
        dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }
}
