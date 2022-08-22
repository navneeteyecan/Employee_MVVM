package com.navneet.test.repositiories;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.navneet.test.models.Employee;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeRepostitory {
    private static EmployeeRepostitory instance;
    private ArrayList<Employee> arrayList = new ArrayList<>();
    Context context;
    String url ="https://mocki.io/v1/61cf7d91-a7f8-405e-b505-67926b759d78";
    MutableLiveData<List<Employee>> data;

    public static EmployeeRepostitory getInstance(){
        if (instance == null){
            instance = new EmployeeRepostitory();
        }
        return instance;
    }
    public MutableLiveData<List<Employee>> getEmployeelist(Context context){
        this.context = context;
        loadEmployees();

        data = new MutableLiveData<>();
        data.setValue(arrayList);
        return data;
    }

    private void loadEmployees() {

        RequestQueue requestQueue = Volley.newRequestQueue( context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("from repository",response.toString());

                        try {
                            String output = (String) response.opt("status");
//                       Checking if data received is proper
                            if(output.equals("success")) {
                                JSONArray jsonArray = response.optJSONArray("data");
                                for (int i = 0; i < Objects.requireNonNull(jsonArray).length(); i++) {
                                        JSONObject employee = jsonArray.getJSONObject(i);
                                        String name = employee.getString("employee_name");
                                        int id = employee.getInt("id");
                                        int salary = employee.getInt("employee_salary");
                                        int employee_age = employee.getInt("employee_age");
                                        arrayList.add(new Employee(id, employee_age, name, "img", (long) salary));
                                    }
                                data.postValue(arrayList);

                            }else{
                                Toast.makeText(context, "Error in parsing Data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error in loading Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, error -> Log.e("from respository",error.toString()));
        requestQueue.add(jsonObjectRequest);

    }
}
