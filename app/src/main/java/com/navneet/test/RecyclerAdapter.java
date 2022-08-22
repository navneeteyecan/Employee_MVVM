package com.navneet.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.navneet.test.models.Employee;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    public RecyclerAdapter(ArrayList<Employee> employeeArrayList) {
        this.employeeArrayList = employeeArrayList;
    }

    ArrayList<Employee> employeeArrayList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_list,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.id.setText(String.valueOf(employeeArrayList.get(position).getId()));
        holder.name.setText(employeeArrayList.get(position).getName());
        holder.age.setText(String.valueOf(employeeArrayList.get(position).getAge()));
        holder.salary.setText(String.valueOf(employeeArrayList.get(position).getSalary()));

        boolean isExpanded= employeeArrayList.get(position).isExpanded();
        holder.salaryContainer.setVisibility(isExpanded ? View.VISIBLE : View.GONE);


        holder.layoutContainer.setOnClickListener((View view) -> {
            employeeArrayList.get(position).setExpanded(!isExpanded);
            notifyItemChanged(position);
        });

    }



    @Override
    public int getItemCount() {
        return employeeArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, id, salary, age;
        LinearLayout layoutContainer, salaryContainer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_view);
            id = itemView.findViewById(R.id.id_view);
            salary = itemView.findViewById(R.id.salary_view);
            age = itemView.findViewById(R.id.age_view);
            layoutContainer = itemView.findViewById(R.id.layout_contianer);
            salaryContainer= itemView.findViewById(R.id.salary_layout_contianer);
        }
    }
}