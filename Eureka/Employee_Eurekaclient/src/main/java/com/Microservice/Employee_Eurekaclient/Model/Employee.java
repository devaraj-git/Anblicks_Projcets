package com.Microservice.Employee_Eurekaclient.Model;

public class Employee {
    private String empName;
    private String department;

    public Employee(String empName, String department) {
        this.empName = empName;
        this.department = department;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
