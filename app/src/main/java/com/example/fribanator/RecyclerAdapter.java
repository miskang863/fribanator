package com.example.fribanator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Rata> radat = new ArrayList<>();
    private OnItemClickListener listener;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt;
        private TextView addressTxt;
        private TextView fairwayTxt;


        public MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.nameTxt);
            addressTxt = view.findViewById(R.id.addressTxt);
            fairwayTxt = view.findViewById(R.id.fairwayTxt);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(radat.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rata_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder myViewHolder, int position) {
        String name = radat.get(position).getName();
        String address = radat.get(position).getAddress();
        // String fairway = radat.get(position).getParList().toString();
        Log.d("demotest", "onBindViewHolder: " + address);

        myViewHolder.nameTxt.setText(name);
        myViewHolder.addressTxt.setText(address);
        myViewHolder.fairwayTxt.setText("Väyliä: " + radat.get(position).getParList().size());
    }

    @Override
    public int getItemCount() {
        return radat.size();
    }

    public void setRadat(List<Rata> radat) {
        this.radat = radat;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Rata rata);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}