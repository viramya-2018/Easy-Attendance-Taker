package com.example.madt_innovative;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 18-Apr-18.
 */

public class CustomAdapter_StudentRoll extends RecyclerView.Adapter<CustomAdapter_StudentRoll.myViewHolder> {
    private ArrayList<String> arrayList;
    private Context context;
    public CustomAdapter_StudentRoll(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
    }

    public void remove(int position){
        arrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String str, int position) {
        arrayList.add(position, str);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public myViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.rv_textView);
        }
    }
}
