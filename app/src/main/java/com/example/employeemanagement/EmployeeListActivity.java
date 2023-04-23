package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.employeemanagement.adapters.BaseSalariedEmployeeAdapter;
import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.roomdb.EmployeeDB;
import com.example.employeemanagement.utils.ConstantUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {
    private Spinner empTypeSP;
    private RecyclerView empRV;
    private BaseSalariedEmployeeAdapter employeeAdapter;

    private String[] empList = {
            ConstantUtils.EmployeeType.BASE_SALARIED,
            ConstantUtils.EmployeeType.HOURLY_SALARIED,
            ConstantUtils.EmployeeType.BASE_COMMISSION
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        setTitle("Employee List");
        empTypeSP = findViewById(R.id.empTypeSP);
        empRV = findViewById(R.id.empRV);
        List<BasedSalariedEmployee> bseList =
                EmployeeDB.getInstance(this)
                .getBaseSalariedEmpDao()
                .getAllBaseSalariedEmployee();
        employeeAdapter = new BaseSalariedEmployeeAdapter(this, bseList);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        empList);
        empTypeSP.setAdapter(adapter);

        empTypeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = empList[position];
                Toast.makeText(EmployeeListActivity.this, selectedType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        LinearLayoutManager llm = new LinearLayoutManager(this);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        //llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        empRV.setLayoutManager(llm);
        empRV.setAdapter(employeeAdapter);

    }

    private List<BasedSalariedEmployee> generateEmployeeDummyList(){
        List<BasedSalariedEmployee> bselist = new ArrayList<>();
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("def","01/01/1990","test@gmail.com","789456123","Ast.Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("efg","01/01/1990","test@gmail.com","789456123","CEO","Male",125000.0));
        bselist.add(new BasedSalariedEmployee("hij","01/01/1998","test@gmail.com","454546446","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1996","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","000022369","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1994","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1993","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("abc","01/01/1990","test@gmail.com","789456123","Manager","Male",25000.0));
        bselist.add(new BasedSalariedEmployee("xyz","01/01/1990","test@gmail.com","017996325","Messenger","Male",25000.0));
        return bselist;
    }
}
