package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.employeemanagement.prefs.AuthPreference;
import com.example.employeemanagement.utils.ConstantUtils;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private AuthPreference authPreference;
    private TextInputEditText emailET, passET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = findViewById(R.id.adminEmailInput);
        passET = findViewById(R.id.adminPasswordInput);
        authPreference = new AuthPreference(this);
        if (authPreference.isAdminLoggedIn()){
            goToDashboardActivity();
        }
    }

    public void loginAdmin(View view) {
        String email = emailET.getText().toString();
        String password = passET.getText().toString();

        if (email.equals(ConstantUtils.Admin.EMAIL_ADDRESS) &&
        password.equals(ConstantUtils.Admin.PASSWORD)){
            authPreference.setLoginStatus(true);
            goToDashboardActivity();
        }else{
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToDashboardActivity(){
        finish();
        startActivity(new Intent(LoginActivity.this, AdminDashboardActivity.class));
    }
}
