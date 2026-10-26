package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class  InvAct extends AppCompatActivity {

    RecyclerView gridView;
    Button manageBtn;

    List<ItemDB> itemList;

    InvCellAdapt cellAdapt;
    AppDB invDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);

        gridView = findViewById(R.id.gridRecyclerView);
        manageBtn = findViewById(R.id.manageBtn);

        gridView.setLayoutManager(new LinearLayoutManager(this));

        invDB = AppDB.getDatabase(this);

        loadItems();

        manageBtn.setOnClickListener(v -> {
            Intent btnIntent = new Intent(InvAct.this, InvManAct.class);
            startActivity(btnIntent);
        });
    }

    //load items from database
    private void loadItems(){
        AppDB ItemData = AppDB.getDatabase(this);
        itemList = ItemData.itemDao().getAllItems();

        cellAdapt = new InvCellAdapt(itemList, ItemData.itemDao());
        gridView.setAdapter(cellAdapt);
    }

    //resume function will refresh inventory screen when coming from management screen.
    @Override
    protected void onResume() {
        super.onResume();

        itemList = invDB.itemDao().getAllItems();

        cellAdapt = new InvCellAdapt(itemList, invDB.itemDao());
        gridView.setAdapter(cellAdapt);
    }
}