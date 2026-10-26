package com.example.myapplication;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    //User login info is verified against database using query
    @Query("SELECT * FROM Users WHERE userName= :userName AND userPassWord = :userPassWord")
    UserDB login(String userName, String userPassWord);

    @Query("SELECT * FROM Users WHERE userName= :userName")
    UserDB getUserByUserName(String userName);

    @Insert
    void insertUser(UserDB user);

}
