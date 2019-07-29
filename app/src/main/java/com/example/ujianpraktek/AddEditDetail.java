package com.example.ujianpraktek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ujianpraktek.data.Reminder;
import com.example.ujianpraktek.viewmodel.MainViewModel;

public class AddEditDetail extends AppCompatActivity {
    private String requestCode;

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_detail);
        
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        Button buat = (Button)findViewById(R.id.btn_tambah);
        Button selesai = findViewById(R.id.btn_selesai);
        final Intent intent = getIntent();
        requestCode = intent.getStringExtra("request");
        if(requestCode.equals("1")){
            getSupportActionBar().setTitle("Tambahkan Pengingat");
            buat.setText("Buat");
            buat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveReminder();
                    Toast.makeText(AddEditDetail.this, "Berhasil membuat pengingat baru", Toast.LENGTH_SHORT).show();
                }
            });
            selesai.setEnabled(false);
        }else if(requestCode.equals("2")){
            getSupportActionBar().setTitle("Ubah Pengingat");
            EditText Kegiatan = findViewById(R.id.et_kegiatan);
            EditText Keterangan = findViewById(R.id.et_keterangan);
            EditText Waktu = findViewById(R.id.et_waktu);

            Kegiatan.setText(intent.getStringExtra("nama"));
            Keterangan.setText(intent.getStringExtra("keterangan"));
            Waktu.setText(intent.getStringExtra("waktu"));
            
            buat.setText("Ubah");
            buat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateReminder();
                    Toast.makeText(AddEditDetail.this, "Mengubah data reminder", Toast.LENGTH_SHORT).show();
                }
            });

            selesai.setEnabled(true);
            selesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = getIntent();
                    int id = intent.getIntExtra("kode", -1);
                    deleteReminder(id);
                    Toast.makeText(AddEditDetail.this, "Reminder Telah Terhapus", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddEditDetail.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }
    }

    private void deleteReminder(int id) {
        this.viewModel.deleteReminder(id);
    }

    private void updateReminder() {
        EditText Kegiatan = findViewById(R.id.et_kegiatan);
        EditText Keterangan = findViewById(R.id.et_keterangan);
        EditText Waktu = findViewById(R.id.et_waktu);

        Intent intent = getIntent();
        int id = intent.getIntExtra("kode", -1);
        String txtKegiatan = Kegiatan.getText().toString();
        String txtKet = Keterangan.getText().toString();
        String txtWaktu = Waktu.getText().toString();
        Reminder r = new Reminder(id, txtKegiatan,txtKet,txtWaktu);
        this.viewModel.updateReminder(r);
    }

    private void saveReminder() {
        EditText Kegiatan = findViewById(R.id.et_kegiatan);
        EditText Keterangan = findViewById(R.id.et_keterangan);
        EditText Waktu = findViewById(R.id.et_waktu);

        String txtKegiatan = Kegiatan.getText().toString();
        String txtKet = Keterangan.getText().toString();
        String txtWaktu = Waktu.getText().toString();

        Reminder r = new Reminder(0, txtKegiatan,txtKet,txtWaktu);
        this.viewModel.saveReminder(r);
    }

    public void back(View view) {
        Intent i = new Intent(AddEditDetail.this, MainActivity.class);
        startActivity(i);
    }
}
