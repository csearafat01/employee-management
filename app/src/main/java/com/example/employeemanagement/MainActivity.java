package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.prefs.AuthPreference;
import com.example.employeemanagement.roomdb.EmployeeDB;
import com.example.employeemanagement.utils.ConstantUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText nameET, dobET, phoneET,
            emailET, baseSalaryET, totalHourET,
            hourlyRateET, commissionRateET, grossSaleET;

    private Button regBtn, updateBtn;
    private BasedSalariedEmployee bse = null;

    private long empID = 0;

    private RadioGroup genderRG, typeRG;
    private String gender = "Male";
    private String emp_type = "Base Salaried Employee";
    private String dob = "";
    private AuthPreference authPreference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_settings:
                Toast.makeText(this, "coming soon...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_logout:
                authPreference.setLoginStatus(false);
                finish();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empID = getIntent().getLongExtra("id", -1);

        regBtn = findViewById(R.id.btn_reg);
        updateBtn = findViewById(R.id.btn_edit);
        authPreference = new AuthPreference(this);

        nameET = findViewById(R.id.empNameInput);
        dobET = findViewById(R.id.empDobInput);
        emailET = findViewById(R.id.empEmailInput);
        phoneET = findViewById(R.id.empPhoneInput);
        baseSalaryET = findViewById(R.id.baseSalaryInput);
        totalHourET = findViewById(R.id.totalHourInput);
        hourlyRateET = findViewById(R.id.hourlyRateInput);
        commissionRateET = findViewById(R.id.commissionRateInput);
        grossSaleET = findViewById(R.id.grossSaleInput);
        genderRG = findViewById(R.id.genderRG);
        typeRG = findViewById(R.id.empTypeRG);

        if (empID > 0){
            regBtn.setVisibility(View.GONE);
            updateBtn.setVisibility(View.VISIBLE);
            dobET.setEnabled(false);
            bse = EmployeeDB.getInstance(this)
                    .getBaseSalariedEmpDao()
                    .getBSEById(empID);

            nameET.setText(bse.getEmp_name());
            dobET.setText(bse.getEmp_dob());
            emailET.setText(bse.getEmp_email());
            phoneET.setText(bse.getEmp_phone());
            baseSalaryET.setText(String.valueOf(bse.getBase_salary()));

        }



        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                gender = rb.getText().toString();
                //Toast.makeText(MainActivity.this, rb.getText().toString(), Toast.LENGTH_LONG).show();

            }
        });

        typeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                emp_type = rb.getText().toString();
                activateDeactivateViews(checkedId);
                //Toast.makeText(MainActivity.this, rb.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void activateDeactivateViews(int checkedId) {
        switch (checkedId){
            case R.id.rbTypeBaseSalariedEmployee:
                baseSalaryET.setVisibility(View.VISIBLE);
                totalHourET.setVisibility(View.GONE);
                hourlyRateET.setVisibility(View.GONE);
                commissionRateET.setVisibility(View.GONE);
                grossSaleET.setVisibility(View.GONE);
                break;

            case R.id.rbTypeHourlySalariedEmployee:
                baseSalaryET.setVisibility(View.GONE);
                totalHourET.setVisibility(View.VISIBLE);
                hourlyRateET.setVisibility(View.VISIBLE);
                commissionRateET.setVisibility(View.GONE);
                grossSaleET.setVisibility(View.GONE);
                break;

            case R.id.rbTypeBasePlusCommissionedSalariedEmployee:
                baseSalaryET.setVisibility(View.VISIBLE);
                totalHourET.setVisibility(View.GONE);
                hourlyRateET.setVisibility(View.GONE);
                commissionRateET.setVisibility(View.VISIBLE);
                grossSaleET.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void registerNewEmployee(View view) {
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String mobile = phoneET.getText().toString();

        switch (emp_type){
            case ConstantUtils
                    .EmployeeType.BASE_SALARIED:
                String baseSalary = baseSalaryET.getText().toString();

                BasedSalariedEmployee bse =
                        new BasedSalariedEmployee(name,dob,email,
                                mobile,emp_type,gender,
                                Double.parseDouble(baseSalary));

                final long empID = EmployeeDB.getInstance(this)
                        .getBaseSalariedEmpDao()
                        .insertBaseSalariedEmployee(bse);

                if (empID > 0){
                    Toast.makeText(this, "saved successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, EmployeeListActivity.class));
                }

                Log.e("Employee", "registerNewEmployee: "+bse);
                break;

            case ConstantUtils.EmployeeType.HOURLY_SALARIED:
                /*String totalHour = totalHourET.getText().toString();
                String hourlyRate = hourlyRateET.getText().toString();

                HourlySalariedEmployee hse = new HourlySalariedEmployee();

                hse.setEmp_name(name);
                hse.setEmp_dob(dob);
                hse.setEmp_email(email);
                hse.setEmp_phone(mobile);
                hse.setEmp_gender(gender);
                hse.setEmp_designation(emp_type);
                hse.setHourly_rate(Double.parseDouble(hourlyRate));
                hse.setTotal_hour(Double.parseDouble(totalHour));
                Log.e("Employee", "registerNewEmployee: "+hse);*/

                break;

            case ConstantUtils.EmployeeType.BASE_COMMISSION:
                String baseSalary1 = baseSalaryET.getText().toString();
                String commissionRate = commissionRateET.getText().toString();
                String grossSale = grossSaleET.getText().toString();

                /*BasePlusCommissionedEmployee bpce = new BasePlusCommissionedEmployee();

                bpce.setEmp_name(name);
                bpce.setEmp_dob(dob);
                bpce.setEmp_email(email);
                bpce.setEmp_phone(mobile);
                bpce.setEmp_gender(gender);
                bpce.setEmp_designation(emp_type);
                bpce.setBase_salary(Double.parseDouble(baseSalary1));
                bpce.setCommission_rate(Double.parseDouble(commissionRate));
                bpce.setGross_sale(Double.parseDouble(grossSale));
                Log.e("Employee", "registerNewEmployee: "+bpce);*/
                break;
        }
    }

    public void showDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        //get the current date from calendar
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this,
                        this,year, month, day);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        dob = sdf.format(calendar.getTime());
        dobET.setText(dob);
    }

    public void updateEmployee(View view) {
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String mobile = phoneET.getText().toString();
        String baseSalary = baseSalaryET.getText().toString();

        bse.setEmp_name(name);
        bse.setEmp_phone(mobile);
        bse.setEmp_email(email);
        bse.setBase_salary(Double.parseDouble(baseSalary));

        final int updatedRow =
                EmployeeDB.getInstance(this)
                .getBaseSalariedEmpDao()
                .updateBaseSalariedEmployee(bse);

        if (updatedRow > 0){
            Toast.makeText(this, "updated successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, EmployeeListActivity.class));
        }

    }
}
