package com.navneet.mvvmEmploye.models;

public class Employee { int id;
    String employee_name;
    int employee_salary;
    int employee_age;
    String profile_image;
    boolean isExpanded;

    public Employee(int id, String employee_name, int employee_salary, int employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return employee_age;
    }

    public void setAge(int age) {
        this.employee_age = age;
    }

    public String getName() {
        return employee_name;
    }

    public void setName(String name) {
        this.employee_name = name;
    }

    public String getImage() {
        return profile_image;
    }

    public void setImage(String image) {
        this.profile_image = image;
    }

    public int getSalary() {
        return employee_salary;
    }


    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public void setSalary(int salary) {
        this.employee_salary = salary;
    }
}
