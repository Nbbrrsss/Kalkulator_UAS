package com.example.kalkulatorku;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SharedPreferencesAdapter extends RecyclerView.Adapter<SharedPreferencesAdapter.ExampleViewHolder> {
    private final ArrayList<ItemList> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLineOperasi;
        public TextView mTextViewLine2;
        public TextView mTextViewLineHasil;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
            mTextViewLineOperasi = itemView.findViewById(R.id.textview_operasi);
            mTextViewLine2 = itemView.findViewById(R.id.textview_line2);
            mTextViewLineHasil = itemView.findViewById(R.id.textview_hasil);

        }
    }

    public SharedPreferencesAdapter(ArrayList<ItemList> mExampleList) {
        this.mExampleList = mExampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ItemList currentItem = mExampleList.get(position);

        holder.mTextViewLine1.setText(currentItem.getLine1());
        holder.mTextViewLine2.setText(currentItem.getLine2());
        holder.mTextViewLineOperasi.setText(currentItem.getLineOperasi());
        holder.mTextViewLineHasil.setText(currentItem.getLineHasil());

    }

    @Override
    public int getItemCount() { return mExampleList.size(); }
}
