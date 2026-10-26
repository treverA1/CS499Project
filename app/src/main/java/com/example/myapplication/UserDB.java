package com.example.myapplication;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UserDB {
    //Unique ID for each user
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String userName;

    //Password here is not encrypted and won't be in future uses in this project, as it is not required
    //If it was real world It would be encrypted here
    public String userPassWord;

    //Function to create new users
    public UserDB(String userName, String userPassWord){
        this.userName = userName;
        this.userPassWord = userPassWord;
    }
}