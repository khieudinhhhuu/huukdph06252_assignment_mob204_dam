package com.khieuthichien.huukdph06252_assignment_mob204_dam.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khieuthichien.huukdph06252_assignment_mob204_dam.R;
import com.khieuthichien.huukdph06252_assignment_mob204_dam.model.Thongke;

import java.util.List;

public class ThongkeAdapter extends RecyclerView.Adapter<ThongkeAdapter.ThongkeHolder> {

    private Context context;
    private List<Thongke> thongkes;

    public ThongkeAdapter(Context context, List<Thongke> thongkes) {
        this.context = context;
        this.thongkes = thongkes;
    }

    @NonNull
    @Override
    public ThongkeAdapter.ThongkeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thongke, parent, false);
        return new ThongkeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongkeAdapter.ThongkeHolder holder, int position) {
        Thongke thongke = thongkes.get(position);
        holder.imganhthongke.setImageResource(R.drawable.thongke);
        holder.tvthongke.setText(thongke.getThongke());
        holder.tvsoluongthongke.setText(thongke.getSoluongthongke()+"");
    }

    @Override
    public int getItemCount() {
        if (thongkes == null){
            return 0;
        }
        return thongkes.size();
    }

    public class ThongkeHolder extends RecyclerView.ViewHolder {

        public final ImageView imganhthongke;
        public final TextView tvthongke;
        public final TextView tvsoluongthongke;

        public ThongkeHolder(View itemView) {
            super(itemView);

            imganhthongke =  itemView.findViewById(R.id.imganhthongke);
            tvthongke =  itemView.findViewById(R.id.tvthongke);
            tvsoluongthongke =  itemView.findViewById(R.id.tvsoluongthongke);

        }

    }
}
