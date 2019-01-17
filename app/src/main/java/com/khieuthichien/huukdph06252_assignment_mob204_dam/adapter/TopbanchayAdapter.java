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
import com.khieuthichien.huukdph06252_assignment_mob204_dam.model.Topbanchay;

import java.util.List;

public class TopbanchayAdapter extends RecyclerView.Adapter<TopbanchayAdapter.TopbanchayHolder> {

    private Context context;
    private List<Topbanchay> topbanchays;

    public TopbanchayAdapter(Context context, List<Topbanchay> topbanchays) {
        this.context = context;
        this.topbanchays = topbanchays;
    }

    @NonNull
    @Override
    public TopbanchayAdapter.TopbanchayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topbanchay, parent, false);
        return new TopbanchayHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopbanchayAdapter.TopbanchayHolder holder, int position) {
        Topbanchay topbanchay = topbanchays.get(position);
        holder.imganhtopbanchay.setImageResource(R.drawable.top);
        holder.tvmasach.setText(topbanchay.getMasach());
        holder.tvsoluong.setText(topbanchay.getSoluong());
    }

    @Override
    public int getItemCount() {
        if (topbanchays == null){
            return 0;
        }
        return topbanchays.size();
    }

    public class TopbanchayHolder extends RecyclerView.ViewHolder {

        public final ImageView imganhtopbanchay;
        public final TextView tvmasach;
        public final TextView tvsoluong;

        public TopbanchayHolder(View itemView) {
            super(itemView);

            imganhtopbanchay =  itemView.findViewById(R.id.imganhtopbanchay);
            tvmasach =  itemView.findViewById(R.id.tvmasach);
            tvsoluong =  itemView.findViewById(R.id.tvsoluong);

        }
    }
}
