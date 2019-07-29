package com.example.ujianpraktek.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ujianpraktek.data.Reminder;
import com.example.ujianpraktek.repository.ReminderRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public ReminderRepository reminderRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.reminderRepository = new ReminderRepository(application);
    }

    public LiveData<List<Reminder>> getReminderList(){return  this.reminderRepository.getReminderList();}

    public void saveReminder(Reminder reminder){this.reminderRepository.SaveReminder(reminder);}

    public void deleteReminder(int id){this.reminderRepository.DeleteReminder(id);}

    public void updateReminder(Reminder reminder){this.reminderRepository.UpdateReminder(reminder);}
}
