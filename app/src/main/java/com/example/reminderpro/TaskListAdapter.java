package com.example.reminderpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    private List<Tasks> taskslist;
    private ResultListener monResultListener;
    Context context;

    public TaskListAdapter(Context context, List<Tasks> taskslist, ResultListener onResultListener1) {
        this.taskslist = taskslist;
        this.context = context;
        this.monResultListener=onResultListener1;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.taskitemlayout,parent,false);
        //return new ViewHolder(view);
        return new TaskListAdapter.ViewHolder(view,monResultListener);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(taskslist.get(position).getActualTask(),position);
    }

    @Override
    public int getItemCount() {
        return taskslist.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // private CheckBox checkBox;
        private TextView taskName;
        ResultListener onResultListener1;

        public ViewHolder(@NonNull View itemView, ResultListener onResultListener1) {
            super(itemView);
           // checkBox=itemView.findViewById(R.id.checkBoxMedicine);
           // medicine = itemView.findViewById(R.id.medicineItemLayoutMedicine);
           // time=  itemView.findViewById(R.id.medicineItemLayoutTime);
            taskName=itemView.findViewById(R.id.taskname);
            itemView.setOnClickListener(this);
            this.onResultListener1=onResultListener1;
        }

        public void setData(String a,int position){
            position=position+1;
            taskName.setText(position+". "+a);
        }

        @Override
        public void onClick(View v) {
            onResultListener1.onResultClick(getAdapterPosition());
        }
    }
    public  interface ResultListener{
        void onResultClick(int position);
    }
}
