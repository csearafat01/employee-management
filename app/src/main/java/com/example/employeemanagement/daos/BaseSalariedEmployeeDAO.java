package com.example.employeemanagement.daos;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeemanagement.entities.BasedSalariedEmployee;

import java.util.List;

@Dao
public interface BaseSalariedEmployeeDAO {

    @Insert
    long insertBaseSalariedEmployee(BasedSalariedEmployee bse);

    @Update
    int updateBaseSalariedEmployee(BasedSalariedEmployee bse);

    @Delete
    int deleteBaseSalariedEmployee(BasedSalariedEmployee bse);

    @Query("select * from tbl_base_salaried")
    List<BasedSalariedEmployee> getAllBaseSalariedEmployee();

    @Query("select * from tbl_base_salaried where empId like :empID")
    BasedSalariedEmployee getBSEById(long empID);
}
