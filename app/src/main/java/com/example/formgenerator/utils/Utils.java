package com.example.formgenerator.utils;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;

public class Utils {

    public Button button(Context context, @ColorRes int color, String text){

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button button = new Button(context);
        button.setBackgroundColor(context.getResources().getColor(color));
        button.setText(text);
        button.setLayoutParams(params);
        Toast.makeText(context, "button created", Toast.LENGTH_SHORT).show();
        return button;
    }

    public TextView textView(Context context, int color, String text, int size){

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(context);
        textView.setBackgroundColor(color);
        textView.setText(text);
        textView.setTextSize(size);
        textView.setLayoutParams(params);
        Toast.makeText(context, "text created", Toast.LENGTH_SHORT).show();
        return textView;
    }


    public EditText editText(Context context, int color, String text){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText editText = new EditText(context);
        editText.setBackgroundColor(color);
        editText.setHint(text);
        editText.setLayoutParams(params);
        editText.setTag(text);
        Toast.makeText(context, "text created", Toast.LENGTH_SHORT).show();
        return editText;
    }


}
