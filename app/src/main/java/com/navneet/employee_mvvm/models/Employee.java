package com.navneet.employee_mvvm.models;

public class Employee { int id, age; String name, image; Long salary;
    boolean isExpanded;



    public Employee(int id, int age, String name, String image, Long salary) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.image = image;
        this.salary = salary;
        this.isExpanded = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getSalary() {
        return salary;
    }


    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
