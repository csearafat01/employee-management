package com.example.employeemanagement.utils;

public class ConstantUtils {

    public static class Admin{
        public static final String EMAIL_ADDRESS = "admin@pencilbox.com";
        public static final String PASSWORD = "123456";
    }

    public static class AdminPreference{
        public static final String FILE_NAME = "auth_pref";
        public static final String STATUS_KEY = "status";
    }

    public static class EmployeeType{
        public static final String BASE_SALARIED = "Base Salaried Employee";
        public static final String HOURLY_SALARIED = "Hourly Salaried Employee";
        public static final String BASE_COMMISSION = "Base Plus Commissioned Salaried Employee";
    }

}
