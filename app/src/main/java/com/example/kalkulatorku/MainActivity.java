package com.example.kalkulatorku;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<ItemList> mExampleList;
    private RecyclerView mRecycleView;
    private SharedPreferencesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RadioGroup RadioGroup;
    RadioButton Tambah, Kurang, Kali, Bagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        buildRecyclerView();
        setInsertButton();

        Button buttonDelete = findViewById(R.id.btnDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {deleteData();

            }
        });

        RadioGroup = findViewById(R.id.RadioGruop);
        Tambah = findViewById(R.id.Tambah);
        Kurang = findViewById(R.id.Kurang);
        Kali = findViewById(R.id.Kali);
        Bagi = findViewById(R.id.Bagi);

    }


    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("share preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ItemList>>() {}.getType();
        mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        mRecycleView = findViewById(R.id.recyclerview);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SharedPreferencesAdapter(mExampleList);

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mAdapter);
    }

    private void setInsertButton(){
        Button btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText line1 = findViewById(R.id.Angka1);
                EditText line2 = findViewById(R.id.Angka2);
                insertItem(line1.getText().toString(), line2.getText().toString());
                saveData();
            }
        });
    }

    private void insertItem(String line1, String line2){
        int hasil = 0;
        if (Tambah.isChecked()){
            hasil = Integer.parseInt(line1) + Integer.parseInt(line2);
        }else if (Kurang.isChecked()){
            hasil = Integer.parseInt(line1) - Integer.parseInt(line2);
        }else if (Kali.isChecked()){
            hasil = Integer.parseInt(line1) * Integer.parseInt(line2);
        }else if (Bagi.isChecked()){
            hasil = Integer.parseInt(line1) / Integer.parseInt(line2);
        }

        String operasi = "";
        if (Tambah.isChecked()){
            operasi = "+";
        }else if (Kurang.isChecked()){
            operasi = "-";
        }else if (Kali.isChecked()){
            operasi = "x";
        }else if (Bagi.isChecked()){
            operasi = ":";
        }

        mExampleList.add(new ItemList(line1, operasi, line2, String.valueOf(hasil)));
        mAdapter.notifyItemInserted(mExampleList.size());
    }

    private void deleteData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mExampleList.clear();
        mAdapter.notifyDataSetChanged();
    }

}