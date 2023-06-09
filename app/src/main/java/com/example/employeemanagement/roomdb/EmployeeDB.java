package com.example.employeemanagement.roomdb;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.employeemanagement.daos.BaseSalariedEmployeeDAO;
import com.example.employeemanagement.entities.BasePlusCommissionedEmployee;
import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.entities.HourlySalariedEmployee;


@Database(entities = {
        BasedSalariedEmployee.class,
        HourlySalariedEmployee.class,
        BasePlusCommissionedEmployee.class
        }, version = 1)
public abstract class EmployeeDB extends RoomDatabase {
    private static EmployeeDB db;
    public abstract BaseSalariedEmployeeDAO getBaseSalariedEmpDao();

    public static EmployeeDB getInstance(Context context){
        if (db != null){
            return db;
        }

        db = Room.databaseBuilder(context, EmployeeDB.class, "employee_db")
                .allowMainThreadQueries()
                .build();
        return db;
    }


}
