package com.example.employeemanagement.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeemanagement.EmployeeDetailsActivity;
import com.example.employeemanagement.MainActivity;
import com.example.employeemanagement.R;
import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.roomdb.EmployeeDB;

import java.util.List;

/*
*
* step 1 : Inflate the layout
* step 2 : View initialize
* step 3 : Data set
*
* */

public class BaseSalariedEmployeeAdapter extends RecyclerView.Adapter<BaseSalariedEmployeeAdapter.BaseSalariedEmployeeViewHolder> {
    private Context context;
    private List<BasedSalariedEmployee> empList;
    private int count = 0;

    public BaseSalariedEmployeeAdapter(Context context, List<BasedSalariedEmployee> empList) {
        this.context = context;
        this.empList = empList;
    }

    @NonNull
    @Override
    public BaseSalariedEmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //step 1
        count++;
        Log.e("employee", "onCreateViewHolder: "+count);
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.employee_row, parent, false);
        return new BaseSalariedEmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseSalariedEmployeeViewHolder holder, final int position) {
        //step 3
        holder.nameTV.setText(empList.get(position).getEmp_name());
        holder.phoneTV.setText(empList.get(position).getEmp_phone());
        holder.menuTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BasedSalariedEmployee bse = empList.get(position);
                final long empId = bse.getEmpId();
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.employee_row_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.row_item_details:
                                Intent intent = new Intent(context, EmployeeDetailsActivity.class);
                                intent.putExtra("id", empId);
                                context.startActivity(intent);
                                break;

                            case R.id.row_item_edit:
                                Intent intent2 = new Intent(context, MainActivity.class);
                                intent2.putExtra("id", empId);
                                context.startActivity(intent2);
                                break;

                            case R.id.row_item_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setIcon(R.drawable.ic_delete_black_24dp);
                                builder.setTitle("Delete Item");
                                builder.setMessage("Are you sure to delete this item?");
                                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final int deletedRow = EmployeeDB.getInstance(context)
                                                .getBaseSalariedEmpDao()
                                                .deleteBaseSalariedEmployee(bse);
                                        if (deletedRow > 0){
                                            empList.remove(bse);
                                            notifyDataSetChanged();
                                            Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                builder.setNegativeButton("Cancel", null);

                                AlertDialog dialog = builder.create();
                                dialog.show();
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return empList.size();
    }

    public class BaseSalariedEmployeeViewHolder extends RecyclerView.ViewHolder {
        //step 2
        TextView nameTV, phoneTV, menuTV;
        public BaseSalariedEmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.row_empName);
            phoneTV = itemView.findViewById(R.id.row_empPhone);
            menuTV = itemView.findViewById(R.id.row_menu);

        }
    }
}
