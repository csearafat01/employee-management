package com.example.employeemanagement.entities;


import androidx.room.PrimaryKey;

public abstract class Employee{
    @PrimaryKey(autoGenerate = true)
    private long empId;
    private String emp_name;
    private String emp_dob;
    private String emp_email;
    private String emp_phone;
    private String emp_designation;
    private String emp_gender;

    public Employee(String emp_name, String emp_dob, String emp_email, String emp_phone, String emp_designation, String emp_gender) {
        this.emp_name = emp_name;
        this.emp_dob = emp_dob;
        this.emp_email = emp_email;
        this.emp_phone = emp_phone;
        this.emp_designation = emp_designation;
        this.emp_gender = emp_gender;
    }

    public double getTotalSalary(){
        return 0.0;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_dob() {
        return emp_dob;
    }

    public void setEmp_dob(String emp_dob) {
        this.emp_dob = emp_dob;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public String getEmp_designation() {
        return emp_designation;
    }

    public void setEmp_designation(String emp_designation) {
        this.emp_designation = emp_designation;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_name='" + emp_name + '\'' +
                ", emp_dob='" + emp_dob + '\'' +
                ", emp_email='" + emp_email + '\'' +
                ", emp_phone='" + emp_phone + '\'' +
                ", emp_designation='" + emp_designation + '\'' +
                ", emp_gender='" + emp_gender + '\'' +
                '}';
    }
}
