package com.example.formgenerator.loadingDialog;

import android.app.Dialog;
import android.content.Context;
import com.example.formgenerator.R;

public class LoadingDialog {

    Context context;
    Dialog dialog;

   public LoadingDialog(Context mContext){
        context = mContext;
    }

    public void startLoadingDialog(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }
}
