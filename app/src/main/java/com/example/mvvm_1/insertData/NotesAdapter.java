package com.example.mvvm_1.insertData;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_1.R;
import com.example.mvvm_1.task.KpUpdateTaskActivity;
import com.example.mvvm_1.task.Task;

import java.util.List;

class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private Context mCtx;
    private List<Example.Product> noteList;

    public NotesAdapter(Context mCtx, List<Example.Product> noteList) {
        this.mCtx = mCtx;
        this.noteList = noteList;
    }
    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_view, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Example.Product t = noteList.get(position);
        holder.tvData.setText(t.getProductName());
        Log.i("Resp",t.getProductName());
     
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
    class NoteViewHolder extends RecyclerView.ViewHolder  {
        TextView tvData;
        public NoteViewHolder(View itemView) {
            super(itemView);
            tvData = itemView.findViewById(R.id.oktv);
        }
        
    }
}