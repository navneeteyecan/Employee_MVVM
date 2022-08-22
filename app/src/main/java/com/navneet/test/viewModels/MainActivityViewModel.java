package com.navneet.test.viewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.navneet.test.models.Employee;
import com.navneet.test.repositiories.EmployeeRepostitory;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<Employee>> mEmployees;

    Context context;


    public void init(Context context){
        this.context=context;
        if (mEmployees != null){
            return;
        }
        EmployeeRepostitory mRepo = EmployeeRepostitory.getInstance();

        mEmployees= mRepo.getEmployeelist(context);
   }

    public LiveData<List<Employee>> getEmployees(){

        return mEmployees;
    }



}
