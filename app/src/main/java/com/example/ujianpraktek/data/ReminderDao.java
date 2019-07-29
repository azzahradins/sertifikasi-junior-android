package com.example.ujianpraktek.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReminderDao {
    @Query("select * from Reminder")
    LiveData<List<Reminder>> showAll();

    @Insert
    void insertData(Reminder... reminder);

    @Query("delete from Reminder where id = :id")
    void deleteReminder(Integer id);

    @Update
    void updateData(Reminder reminder);
}
