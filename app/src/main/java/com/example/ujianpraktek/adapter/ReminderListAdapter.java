package com.example.ujianpraktek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujianpraktek.R;
import com.example.ujianpraktek.data.Reminder;

import java.util.List;

public class ReminderListAdapter extends RecyclerView.Adapter<ReminderListAdapter.ReminderItemHolder>{
    private Context context;
    private List<Reminder> reminderList;
    onItemClickListener listener;

    //setter getter dari list
    public List<Reminder> getReminderList() {
        return reminderList;
    }
    public void setReminderList(List<Reminder> reminderList) {
        this.reminderList = reminderList;
        this.notifyDataSetChanged();
    }

    //construstor
    public ReminderListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReminderItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(this.context);
        View reminderItemView =inflater.inflate(R.layout.list_reminder,parent, false);
        ReminderItemHolder vhReminderItem = new ReminderItemHolder(reminderItemView);
        return vhReminderItem;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderItemHolder holder, int position) {
        Reminder currentReminder = this.reminderList.get(position);

        String nama = currentReminder.getNamaKegiatan();
        String ket = currentReminder.getKeteranganSingkat();
        String waktu = currentReminder.getWaktuKegiatan();

        holder.tvNama.setText(nama);
        holder.tvKeterangan.setText(ket);
        holder.tvWaktu.setText(waktu);
    }

    @Override
    public int getItemCount() {
        if(this.reminderList == null) return 0;
        else return reminderList.size();
    }

    class ReminderItemHolder extends RecyclerView.ViewHolder {
        private TextView tvNama = itemView.findViewById(R.id.txtNama);
        private TextView tvKeterangan = itemView.findViewById(R.id.txtKeterangan);
        private TextView tvWaktu = itemView.findViewById(R.id.txtWaktu);

        public ReminderItemHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positon = getAdapterPosition();
                    listener.onItemListener(reminderList.get(positon));
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemListener(Reminder reminder);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

}
