package com.navneet.mvvmEmploye;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.navneet.mvvmEmploye.models.Employee;
import com.navneet.mvvmEmploye.viewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RecyclerAdapter mAdapter;
    MainActivityViewModel mainActivityViewModel;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init(this);

        mainActivityViewModel.getEmployees().observe(this, employees -> {

            if(employees.size()>0){
                hideProgressBar();
            }
            mAdapter.notifyDataSetChanged();
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter= new RecyclerAdapter((ArrayList<Employee>) mainActivityViewModel.getEmployees().getValue());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        Log.e("main Activity"," hiding progressbar");
    progressBar.setVisibility(View.INVISIBLE); }
}