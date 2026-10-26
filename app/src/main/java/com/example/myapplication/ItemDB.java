package com.example.myapplication;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Items")
public class ItemDB {
    //Unique ID for each user
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String itemName;

    public int itemQuant;
    public String itemLoc;

    //Function to create new users
    public ItemDB(String itemName, int itemQuant, String itemLoc){
        this.itemName = itemName;
        this.itemQuant = itemQuant;
        this.itemLoc = itemLoc;
    }
}