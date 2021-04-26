package com.yangxuan.nonconstrutor;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

class Employee {

    private String name;
    private Salary salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", salary=" + salary + " ]";
    }
}

class Salary {

    private int salaryAmount;

    Salary(int salary) {
        this.salaryAmount = salary;
    }

    @Override
    public String toString() {
        return "Salary [salaryAmount=" + salaryAmount + "]";
    }
}

class SalaryInstanceCreator implements InstanceCreator<Salary> {
    @Override
    public Salary createInstance(Type type) {
        return new Salary(25000);
    }
}

public class InstanceCreatorUse {

    public static void main(String[] args) {

        String jsonString = "{\"name\" :\"Sandeep\" , \"salary\": {}}";

        Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(Salary.class, new SalaryInstanceCreator())
                .setPrettyPrinting().create();

        System.out.println(gson.fromJson(jsonString, Employee.class));
    }
}
