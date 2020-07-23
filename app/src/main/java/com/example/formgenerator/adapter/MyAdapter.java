package com.example.formgenerator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formgenerator.R;
import com.example.formgenerator.model.Form;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Form> forms;
    private onFormListener onFormListener;

    public MyAdapter(List<Form> forms, onFormListener onFormListener) {
        this.forms = forms;
        this.onFormListener = onFormListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);

        return new MyAdapter.MyViewHolder(view, onFormListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Form form = forms.get(position);

        holder.title.setText(form.getForm_tittle());
        holder.creator.setText(form.getCreator());
        holder.date.setText(form.getInsert_date());

    }

    @Override
    public int getItemCount() {
        return forms.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, creator, date;

        public MyViewHolder(@NonNull View itemView, onFormListener onFormListener) {
            super(itemView);

            title = itemView.findViewById(R.id.form_tittle);
            creator = itemView.findViewById(R.id.creator);
            date = itemView.findViewById(R.id.insert_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                onFormListener.onFormClick(getAdapterPosition());
        }
    }


    public interface onFormListener {
        void onFormClick(int position);
    }


}
