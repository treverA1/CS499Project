package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapplication.ItemDB;

import androidx.appcompat.app.AppCompatActivity;

public class InvManAct extends AppCompatActivity {
    EditText itemName;
    TextView itemData;
    Button incBtn;
    Button decBtn;
    Button delBtn;

    Button returnBtn;

    Button SMSBtn;

    Button addBtn;

    AppDB itemDataBase;
    ItemDB currItem;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_cell);

        itemDataBase = AppDB.getDatabase(this);

        itemName = findViewById(R.id.itemView);
        itemData = findViewById(R.id.itemData);
        incBtn = findViewById(R.id.incBtn);
        decBtn = findViewById(R.id.decBtn);
        delBtn = findViewById(R.id.Delete);
        addBtn = findViewById(R.id.Add);
        returnBtn = findViewById(R.id.returnBtn);
        SMSBtn = findViewById(R.id.messageBtn);

        //test item to lookup
        //itemDataBase.itemDao().insertItem(new ItemDB("Test Item", 123, "C3"));

        itemName.addTextChangedListener(new TextWatcher(){

            @Override
            //This will change the text in the textView for the specific item you looked up
            public void afterTextChanged(Editable s) {
                String name = s.toString();
                currItem = itemDataBase.itemDao().getItemByName(name);

                if (currItem != null){
                    itemData.setText("Item: " + currItem.itemName + "\nQty: " + currItem.itemQuant + "\n Location: " + currItem.itemLoc);

                }
                else{
                    itemData.setText("Item Not Found");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });

        //increase amount of item
        incBtn.setOnClickListener(v -> {
            if (currItem != null){
                itemDataBase.itemDao().increaseQty(currItem.itemName);
                refreshItem();
            }
        });

        //decrease
        decBtn.setOnClickListener(v -> {
            if (currItem != null){
                itemDataBase.itemDao().decreaseQty(currItem.itemName);
                refreshItem();
            }
        });

        //delete
        delBtn.setOnClickListener(v -> {
            if (currItem != null){
                itemDataBase.itemDao().deleteByName(currItem.itemName);
                itemData.setText("Item Deleted");
                itemName.setText("");
                currItem = null;
            }
        });

        addBtn.setOnClickListener(v -> {
            //eliminates any extra space that was added
            String ItName = itemName.getText().toString().trim();

            if (ItName.isEmpty()){
                itemData.setText("Enter item name");
                return;
            }

            ItemDB itemExists = itemDataBase.itemDao().getItemByName(ItName);

            if (itemExists == null){
                ItemDB newItem = new ItemDB(ItName, 1, "Unknown");

                itemDataBase.itemDao().insertItem(newItem);

                itemData.setText("Item Added: " + ItName);

                currItem = newItem;
            }
            else {
                itemData.setText("Item already in system");
            }
        });

        //allows return to same inventory status without creating a new instance
        returnBtn.setOnClickListener(v -> {
            finish();
        });

        SMSBtn.setOnClickListener(v -> {
            Intent btnIntent = new Intent(InvManAct.this, SMSAct.class);
            startActivity(btnIntent);
        });

    }

    //function for refreshing the app whenever a change is made
    private void refreshItem() {
        currItem = itemDataBase.itemDao().getItemByName(currItem.itemName);

        itemData.setText("Item: " + currItem.itemName + "\nQty: " + currItem.itemQuant + "\n Location: " + currItem.itemLoc);
    }
}
