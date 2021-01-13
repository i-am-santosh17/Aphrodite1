package com.example.aphrodite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
    private final LayoutInflater mInflator;
    private List<String> mNotes;
public String subjec;
    NoteListAdapter(Context context) {
        mInflator = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public NoteListAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.note_list_layout, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.NoteViewHolder holder, int position) {
        if (mNotes != null) {
            String current = mNotes.get(position);
            if(current!=null)
            holder.noteItemView.setText(current);

        } else {
            holder.noteItemView.setText("No text");
        }


    }
    void setNotes (List<String> note) {
    mNotes=note;
     notifyDataSetChanged();

    }
    @Override
    public int getItemCount () {
        if (mNotes != null) {
            return mNotes.size();
        }
        return 0;
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteItemView;

        private NoteViewHolder(View itemView) {

            super(itemView);
            noteItemView = itemView.findViewById(R.id.textView);
        }

    }
}