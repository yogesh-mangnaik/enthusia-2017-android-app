package org.enthusia.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.enthusia.R;
import org.enthusia.model.Department;

import java.util.List;

/**
 * Created by Yogesh on 11/19/2017.
 */
public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.MyViewHolder>{

    List<Department> list;

    public LeaderboardAdapter(List<Department> list){
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leaderboard_item, viewGroup, false);
        MyViewHolder pvh = new MyViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(list.get(i).name);
        myViewHolder.points.setText(list.get(i).points + "");
        if(i%2==0){
            myViewHolder.container.setBackgroundColor(Color.parseColor("#222222"));
        }
        else{

            myViewHolder.name.setTextColor(Color.parseColor("#000000"));
            myViewHolder.points.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView points;
        LinearLayout container;
        MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            points = (TextView) itemView.findViewById(R.id.points);
            container = (LinearLayout) itemView.findViewById(R.id.container);
        }
    }

}