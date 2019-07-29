package com.example.ujianpraktek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ujianpraktek.adapter.ReminderListAdapter;
import com.example.ujianpraktek.data.Reminder;
import com.example.ujianpraktek.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //ViewModel
    private MainViewModel viewModel;
    private static final String KEY_REQUEST_INSERT = "1";
    private static final String KEY_REQUEST_UPDATE = "2";
    // Data
    private ReminderListAdapter reminderAdapter;
    private ArrayList<Reminder> reminderList;

    // Components
    private RecyclerView rvReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        this.initData();
        this.initComponent();

        reminderAdapter.setOnItemClickListener(new ReminderListAdapter.onItemClickListener() {
            @Override
            public void onItemListener(Reminder reminder) {
                Intent i = new Intent(MainActivity.this, AddEditDetail.class);
                i.putExtra("request", KEY_REQUEST_UPDATE);
                int id = reminder.getId();
                Log.d("kode", "Ini adalah " + id);
                i.putExtra("kode", id);
                i.putExtra("nama", reminder.getNamaKegiatan());
                i.putExtra("keterangan", reminder.getKeteranganSingkat());
                i.putExtra("waktu", reminder.getWaktuKegiatan());
                startActivity(i);
            }
        });

    }

    public void onBackPressed() {
        finish();
    }

    private void initData() {
        this.reminderList = new ArrayList<>();
        this.reminderAdapter = new ReminderListAdapter(this);
    }

    private void initComponent() {
        //setting layout and select adapter
        this.rvReminder = findViewById(R.id.rv_reminder);
        this.rvReminder.setLayoutManager(new LinearLayoutManager(this));
        this.rvReminder.setAdapter(this.reminderAdapter);
        //load data
        this.reminderAdapter.setReminderList(this.reminderList);

        this.viewModel.getReminderList().observe(this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(List<Reminder> reminders) {
                reminderAdapter.setReminderList(reminders);
            }
        });
    }

    public void createNewKegiatan(View view) {
        Intent i = new Intent(MainActivity.this, AddEditDetail.class);
        i.putExtra("request", KEY_REQUEST_INSERT);
        startActivity(i);
    }
    public void back(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
