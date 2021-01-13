package com.example.aphrodite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public  class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder> {

private final LayoutInflater mInflator;
private List<Subject> mSubjects;
List<String> mNotes;
SubjectListAdapter(Context context){
    mInflator=LayoutInflater.from(context);
}

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =mInflator.inflate(R.layout.subject_list_layout,parent,false);
        return new SubjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
if(mSubjects!=null){
    Subject current=mSubjects.get(position);
    holder.subjectItemView.setText(current.getSubject());

} else {
    holder.subjectItemView.setText("No Subject");
}
    }
    void setSubjects(List<Subject> subjects){
    mSubjects=subjects;
    notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
    if(mSubjects!=null){
        return mSubjects.size();
    }else
        return 0;
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder{
        private final TextView subjectItemView;
        private SubjectViewHolder(View itemView){
            super(itemView);
            subjectItemView=itemView.findViewById(R.id.textView);
        }

    }
}
