package com.example.fribanator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScoreRecyclerAdapter extends RecyclerView.Adapter<ScoreRecyclerAdapter.MyViewHolder> {

    private List<Score> scores = new ArrayList<>();
    private OnItemClickListener listener;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt;
        private TextView dateTxt;
        private TextView scoreTxt;


        public MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.scoreTxt2);
            dateTxt = view.findViewById(R.id.scoreDateTxt);
           // scoreTxt = view.findViewById(R.id.scoreNameTxt);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(scores.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ScoreRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.score_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreRecyclerAdapter.MyViewHolder myViewHolder, int position) {
        String name = scores.get(position).getRataName();
        String date = scores.get(position).getDate();
         //String scoreResult = scores.get(position).getParList().toString();
      //  Log.d("demotest", "onBindViewHolder: " + address);
        myViewHolder.nameTxt.setText(name);
        myViewHolder.dateTxt.setText(date);
     //   myViewHolder.fairwayTxt.setText("Väyliä: " + radat.get(position).getParList().size());
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Score score);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
