package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.employeemanagement.databinding.ActivityEmployeeDetailsBinding;
import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.roomdb.EmployeeDB;

public class EmployeeDetailsActivity extends AppCompatActivity {
    private ActivityEmployeeDetailsBinding binding;
    private long empId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_details);

        empId = getIntent().getLongExtra("id", -1);
        if (empId > 0){
            final BasedSalariedEmployee bse = EmployeeDB.getInstance(this)
                    .getBaseSalariedEmpDao()
                    .getBSEById(empId);
            binding.detailsEmpName.setText(bse.getEmp_name());
            binding.detailsEmpDesignation.setText(bse.getEmp_designation());
            binding.detailsEmpDob.setText(bse.getEmp_dob());
            binding.detailsEmpEmail.setText(bse.getEmp_email());
            binding.detailsEmpPhone.setText(bse.getEmp_phone());
            binding.detailsEmpGender.setText(bse.getEmp_gender());
            binding.detailsEmpBaseSalary.setText(String.valueOf(bse.getBase_salary()));
            binding.detailsEmpPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri phoneUri = Uri.parse("tel:"+bse.getEmp_phone());
                    /*Intent intent = new Intent(Intent.ACTION_DIAL, phoneUri);
                    if (intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(EmployeeDetailsActivity.this, "no component found", Toast.LENGTH_SHORT).show();
                    }*/

                    Intent intent = new Intent(Intent.ACTION_CALL, phoneUri);
                    if (intent.resolveActivity(getPackageManager()) != null){
                        if (isCallPhonePermissionGranted()){
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(EmployeeDetailsActivity.this, "no component found", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private boolean isCallPhonePermissionGranted(){
        String[] permissions = {Manifest.permission.CALL_PHONE};

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED){
            requestPermissions(permissions, 789);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }
}
