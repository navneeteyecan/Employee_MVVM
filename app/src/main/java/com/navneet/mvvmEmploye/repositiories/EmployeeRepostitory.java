package com.navneet.mvvmEmploye.repositiories;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.navneet.mvvmEmploye.JsonPlaceHolderApi;
import com.navneet.mvvmEmploye.models.Employee;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeRepostitory {
    private static EmployeeRepostitory instance;
    private ArrayList<Employee> arrayList = new ArrayList<>();

    Context context;
    String url = "https://mocki.io/v1/";
    MutableLiveData<List<Employee>> data;

    public static EmployeeRepostitory getInstance() {
        if (instance == null) {
            instance = new EmployeeRepostitory();
        }
        return instance;
    }

    public MutableLiveData<List<Employee>> getEmployeelist(Context context) {
        this.context = context;
        loadEmployees();

        data = new MutableLiveData<>();
        data.setValue(arrayList);
        return data;
    }

    private void loadEmployees() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<JsonObject> status = jsonPlaceHolderApi.getStatus();

//        status.enqueue(new Callback<JsonArray>() {
//            @Override
//            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//                Log.e("from repository", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<JsonArray> call, Throwable t) {
//
//            }
//        });
        status.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    Log.e("from repository", response.body().toString());
                    String output = response.body().get("status").getAsString();
                    Log.e("from repository", output);

                    if (output.equals("success")) {
//                Checking if data received is proper
                        JsonArray jsonArray = response.body().getAsJsonArray("data");
                              for (int i = 0; i < Objects.requireNonNull(jsonArray).size(); i++) {
                                  JsonObject employee = jsonArray.get(i).getAsJsonObject();
                                  String name = employee.get("employee_name").getAsString();
                                    int id = employee.get("id").getAsInt();
                                    int salary = employee.get("employee_salary").getAsInt();
                                    int employee_age = employee.get("employee_age").getAsInt();
                                    arrayList.add(new Employee(id,  name,  salary, employee_age, "img"));
                                }
                            data.postValue(arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

//
//        Log.e("Employee",response.body().string());
//        data.postValue(arrayList);
    }

//    private void loadEmployees() {
//
//        RequestQueue requestQueue = Volley.newRequestQueue( context);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, response -> {
//
//                    Log.e("from repository",response.toString());
//
//                    try {
//                        String output = (String) response.opt("status");
////                       Checking if data received is proper
//                        assert output != null;
//                        if(output.equals("success")) {
//                            JSONArray jsonArray = response.optJSONArray("data");
//                            for (int i = 0; i < Objects.requireNonNull(jsonArray).length(); i++) {
//                                    JSONObject employee = jsonArray.getJSONObject(i);
//                                    String name = employee.getString("employee_name");
//                                    int id = employee.getInt("id");
//                                    int salary = employee.getInt("employee_salary");
//                                    int employee_age = employee.getInt("employee_age");
//                                    arrayList.add(new Employee(id,  name,  salary, employee_age, "img"));
//                                }
//                            data.postValue(arrayList);
//
//                        }else{
//                            Toast.makeText(context, "Error in parsing Data", Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Toast.makeText(context, "Error in loading Data", Toast.LENGTH_SHORT).show();
//                    }
//                }, error -> Log.e("from respository",error.toString()));
//        requestQueue.add(jsonObjectRequest);
//
//    }


}
