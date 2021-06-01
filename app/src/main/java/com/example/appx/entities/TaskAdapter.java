package com.example.appx.entities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appx.R;

import java.util.ArrayList;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> tasks;
    private OnTaskClickListener onTaskClickListener;

    public TaskAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public interface OnTaskClickListener {
        void onTaskClick(int position);
        void onLongClick(int position);
    }

    public void setOnTaskClickListener(OnTaskClickListener onTaskClickListener) {
        this.onTaskClickListener = onTaskClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textViewContent.setText(task.getContent());
        holder.textViewStatus.setText(task.getStatus());
        holder.textViewPerson.setText(task.getPerson());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewContent;
        private TextView textViewStatus;
        private TextView textViewPerson;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContent = itemView.findViewById(R.id.textViewContent);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);;
            textViewPerson = itemView.findViewById(R.id.textViewPerson);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTaskClickListener != null) {
                        onTaskClickListener.onTaskClick(getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onTaskClickListener != null) {
                        onTaskClickListener.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}
