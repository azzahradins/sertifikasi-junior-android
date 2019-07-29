package com.example.ujianpraktek.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Reminder.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReminderDao reminderDao();
}
