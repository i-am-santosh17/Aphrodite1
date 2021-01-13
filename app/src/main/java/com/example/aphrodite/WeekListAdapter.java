package com.example.aphrodite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeekListAdapter extends RecyclerView.Adapter<WeekListAdapter.WeekListHolder> {
    private final LayoutInflater mInflator;
    private List<String> mNotes;

    WeekListAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public WeekListAdapter.WeekListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mInflator.inflate(R.layout.note_list_layout, parent, false);
        return new WeekListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekListAdapter.WeekListHolder holder, int position) {
        if (mNotes != null) {
            String current = mNotes.get(position);
            if(current!=null)
            holder.noteItemView.setText(current);

        } else {
            holder.noteItemView.setText("No text");
        }
    }

    @Override
    public int getItemCount() {
        if (mNotes != null) {
            return mNotes.size();

        }
        return 0;
    }
    void setNotes (List<String> note) {
        mNotes=note;
        notifyDataSetChanged();

    }
    class WeekListHolder extends RecyclerView.ViewHolder{

        private final TextView noteItemView;

        private WeekListHolder(View itemView) {

            super(itemView);
            noteItemView = itemView.findViewById(R.id.textView);
        }

    }
    }

