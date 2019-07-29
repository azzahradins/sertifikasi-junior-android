package com.example.ujianpraktek.data;

import android.content.Context;

import androidx.room.Room;

public class AppDbProvider {
    private static AppDatabase appDbInstance;

    public static AppDatabase getAppDbInstance(Context context){
        if(AppDbProvider.appDbInstance == null){
            AppDbProvider.appDbInstance = Room.
                    databaseBuilder(
                            context,
                            AppDatabase.class,
                            "AppDatabase").build();
        }
        return AppDbProvider.appDbInstance;
    }
}
