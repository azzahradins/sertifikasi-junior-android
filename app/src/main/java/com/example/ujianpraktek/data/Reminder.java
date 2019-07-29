package com.example.ujianpraktek.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Reminder")
public class Reminder {

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "namaKegiatan")
    public String namaKegiatan;

    @ColumnInfo(name = "keteranganSingkat")
    public  String keteranganSingkat;

    @ColumnInfo(name = "waktuKegiatan")
    public  String waktuKegiatan;

    public Reminder(@NonNull int id, String namaKegiatan, String keteranganSingkat, String waktuKegiatan) {
        this.id = id;
        this.namaKegiatan = namaKegiatan;
        this.keteranganSingkat = keteranganSingkat;
        this.waktuKegiatan = waktuKegiatan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getKeteranganSingkat() {
        return keteranganSingkat;
    }

    public void setKeteranganSingkat(String keteranganSingkat) {
        this.keteranganSingkat = keteranganSingkat;
    }

    public String getWaktuKegiatan() {
        return waktuKegiatan;
    }

    public void setWaktuKegiatan(String waktuKegiatan) {
        this.waktuKegiatan = waktuKegiatan;
    }
}
