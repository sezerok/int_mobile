package ru.mirea.kharakhorinvd.employeedb;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();
        Employee existingEmployee = employeeDao.getById(1);
        if (existingEmployee == null) {
            Employee employee = new Employee();
            employee.name = "iron man";
            employee.salary = 10000;
            employeeDao.insert(employee);
        } else {
            existingEmployee.salary = 20000;
            employeeDao.update(existingEmployee);
        }

        List<Employee> employees = employeeDao.getAll();
        Employee employee = employeeDao.getById(2);
        if (employee != null) {
            Log.d(TAG, employee.name + " " + employee.salary);
        }
    }
}