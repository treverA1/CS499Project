package com.example.myapplication;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM Items")
    List<ItemDB> getAllItems();

    @Query("DELETE FROM Items WHERE id = :id")
    void deleteItem(int id);

    @Query("SELECT * FROM Items WHERE itemName = :name LIMIT 1")
    ItemDB getItemByName(String name);

    @Query("UPDATE Items SET itemQuant = itemQuant + 1 WHERE itemName = :name")
    void increaseQty(String name);

    @Query("UPDATE Items SET itemQuant = itemQuant - 1 WHERE itemName = :name")
    void decreaseQty(String name);

    @Query("DELETE FROM Items WHERE itemName = :name")
    void deleteByName(String name);
    @Insert
    void insertItem(ItemDB item);

}