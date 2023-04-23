package com.example.employeemanagement.entities;

import androidx.room.Entity;

@Entity
public class BasePlusCommissionedEmployee extends BasedSalariedEmployee{
    private double commission_rate;
    private double gross_sale;

    public BasePlusCommissionedEmployee(String emp_name, String emp_dob, String emp_email, String emp_phone, String emp_designation, String emp_gender, double base_salary,
                                        double commission_rate, double gross_sale) {
        super(emp_name, emp_dob, emp_email, emp_phone, emp_designation, emp_gender, base_salary);
        this.commission_rate = commission_rate;
        this.gross_sale = gross_sale;
    }

    public double getCommission_rate() {
        return commission_rate;
    }

    public void setCommission_rate(double commission_rate) {
        this.commission_rate = commission_rate;
    }

    public double getGross_sale() {
        return gross_sale;
    }

    public void setGross_sale(double gross_sale) {
        this.gross_sale = gross_sale;
    }

    @Override
    public String toString() {
        return super.toString()+"BasePlusCommissionedEmployee{" +
                "commission_rate=" + commission_rate +
                ", gross_sale=" + gross_sale +
                '}';
    }
}
