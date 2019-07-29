package com.example.ujianpraktek.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.ujianpraktek.data.AppDatabase;
import com.example.ujianpraktek.data.AppDbProvider;
import com.example.ujianpraktek.data.Reminder;
import com.example.ujianpraktek.data.ReminderDao;

import java.util.List;

public class ReminderRepository {
    public AppDatabase database;
    public LiveData<List<Reminder>> reminderList;

    public ReminderRepository(Context context){
        this.database = AppDbProvider.getAppDbInstance(context);
    }

    public LiveData<List<Reminder>> getReminderList(){
        this.getReminderDb();
        return this.reminderList;
    }
    private void getReminderDb() {
        ReminderDao dao = this.database.reminderDao();
        this.reminderList = dao.showAll();
    }

    public void SaveReminder(Reminder reminder){
        new saveReminderTask().execute(reminder);
    }
    private class saveReminderTask extends AsyncTask<Reminder, Void, Void> {
        @Override
        protected Void doInBackground(Reminder... reminders) {
            ReminderDao dao = database.reminderDao();
            for(int i = 0; i < reminders.length; i++){
                Reminder n = reminders[i];
                dao.insertData(n);
            }
            return null;
        }
    }

    public void UpdateReminder(Reminder reminder){
        ReminderDao dao = this.database.reminderDao();
        new updateReminderTask(dao).execute(reminder);
    }
    private class updateReminderTask extends AsyncTask<Reminder, Void, Void> {
        private ReminderDao dao;
        public updateReminderTask(ReminderDao reminderDao) {
            this.dao = reminderDao;
        }
        @Override
        protected Void doInBackground(Reminder... reminders) {
            dao.updateData(reminders[0]);
            return null;
        }
    }

    public void DeleteReminder(int id){
        ReminderDao dao = database.reminderDao();
        new deleteReminderTask(dao).execute(id);
    }
    private class deleteReminderTask extends AsyncTask<Integer, Void, Void>{
        private ReminderDao dao;
        public deleteReminderTask(ReminderDao reminderDao) {
            this.dao = reminderDao;
        }
        @Override
        protected Void doInBackground(Integer... reminders) {
            dao.deleteReminder(reminders[0]);
            return null;
        }
    }
}
