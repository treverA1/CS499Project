package com.example.myapplication;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

//Create database table with using room
@Database(entities = {UserDB.class, ItemDB.class}, version = 2  )
public abstract class AppDB extends RoomDatabase{

    //Used in main activity to insert users into the database
    public abstract UserDao userDao();

    public abstract ItemDao itemDao();

    private static AppDB INSTANCE;

    //Database creation
    public static AppDB getDatabase(Context context){
        if(INSTANCE == null){
            //FYI allowMainThreadQueries allows the classes to access the database and will crash without it
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDB.class,"user_db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
