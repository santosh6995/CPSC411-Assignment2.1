package com.example.assignment2;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.assignment2.Model.Course;
import com.example.assignment2.Model.Student;
import com.example.assignment2.Model.StudentDB;
import com.example.assignment2.Model.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetails extends AppCompatActivity {
    protected Student s;

    ArrayList<Vehicle> vehicle;
    protected int studentIndx;
    protected View bt,bt1;
    protected Menu detailMenu;
    protected final String TAG = "Detail Screen";
    private LinearLayout vehiclelinearlayout;
    protected EditText editView0,editView1,editView2;
    protected int position;
    protected int i;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.student_details);

        studentIndx = getIntent().getIntExtra("StudentIndex", 0);
        s = StudentDB.getInstance().getstudentList().get(studentIndx);

        vehiclelinearlayout = (LinearLayout) findViewById(R.id.vehicle_list_id);

        TextView tv = findViewById(R.id.display_string_id);

        int s1=studentIndx+1;
        String origStr = (String) tv.getText();
        tv.setText(origStr + s1);
        tv.setTextSize(20);

        EditText editView = findViewById(R.id.first_name_id);
        editView.setText(s.getFirstname());
        editView.setEnabled(false);
        editView = findViewById(R.id.last_name_id);
        editView.setText(s.getLastname());
        editView.setEnabled(false);
        editView = findViewById(R.id.cwid_id);
        editView.setText(Integer.toString(s.getCwid()));
        editView.setEnabled(false);
      //  bt.findViewById(R.id.btn).setEnabled(false);
        bt= findViewById(R.id.btn);
        bt.setVisibility(View.GONE);
        bt1=findViewById(R.id.btn1);
        bt1.setVisibility(View.GONE);

        for(i=0;i<s.getVehicleid().size();i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.vehicle_row, null);

            Vehicle c = s.getVehicleid().get(i);
            rowView.setId(new Integer(i));


            EditText ed = rowView.findViewById(R.id.veh_make_id);
            ed.setText(c.getMake());
            ed.setEnabled(false);
            EditText ed1 = rowView.findViewById(R.id.veh_model_id);
            ed1.setText(c.getModel());
            ed1.setEnabled(false);
            EditText ed2 = rowView.findViewById(R.id.veh_year_id);
            ed2.setText(Integer.toString(c.getyear()));
            ed2.setEnabled(false);
            rowView.setTag(ed);

            vehiclelinearlayout.addView(rowView, vehiclelinearlayout.getChildCount() - 1);


        }









    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Custom Menu inflation
        getMenuInflater().inflate(R.menu.detail_screen_menu, menu);
        menu.findItem(R.id.action_edit).setVisible(true);
        menu.findItem(R.id.action_done).setVisible(false);

        detailMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            EditText editView = findViewById(R.id.first_name_id);
            editView.setEnabled(true);
            editView = findViewById(R.id.last_name_id);
            editView.setEnabled(true);
            editView = findViewById(R.id.cwid_id);
            editView.setEnabled(true);
            //
            item.setVisible(false);

            bt.findViewById(R.id.btn).setVisibility(View.VISIBLE);



            detailMenu.findItem(R.id.action_done).setVisible(true);
        } else if (item.getItemId() == R.id.action_done) {
            EditText editView = findViewById(R.id.first_name_id);

            StudentDB.getInstance().getstudentList().get(studentIndx).Setfirstname(editView.getText().toString());
            editView.setEnabled(false);
            editView = findViewById(R.id.last_name_id);
            System.out.println(editView.getText().toString());
            StudentDB.getInstance().getstudentList().get(studentIndx).Setlastname(editView.getText().toString());
            editView.setEnabled(false);
            item.setVisible(false);
            editView = findViewById(R.id.cwid_id);
            System.out.println(s.getFirstname());
            StudentDB.getInstance().getstudentList().get(studentIndx).SetCwid(Integer.valueOf(editView.getText().toString()));
            editView.setEnabled(false);
            editView = findViewById(R.id.veh_make_id);
            editView.setEnabled(false);
            editView = findViewById(R.id.veh_model_id);
            editView.setEnabled(false);
            editView = findViewById(R.id.veh_year_id);
            editView.setEnabled(false);
            item.setVisible(false);
            bt.setVisibility(View.GONE);
            detailMenu.findItem(R.id.action_edit).setVisible(true);



        }
        return super.onOptionsItemSelected(item);
    }

    public void addvehicle(View v) {
        LayoutInflater inflaters = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflaters.inflate(R.layout.vehicle_rows, null);



        vehiclelinearlayout.addView(rowView, vehiclelinearlayout.getChildCount() - 1);
        bt1.findViewById(R.id.btn1).setVisibility(View.VISIBLE);
        bt.findViewById(R.id.btn).setVisibility(View.GONE);
        detailMenu.findItem(R.id.action_done).setVisible(false);


    }
    public void Complete(View v) {
        editView0 = findViewById(R.id.veh_make_ids);
        editView1 = findViewById(R.id.veh_model_ids);
        editView2 = findViewById(R.id.veh_year_ids);
        if(TextUtils.isEmpty(editView0.getText())){

            editView0.setError( "Make is required!" );

        } else if(TextUtils.isEmpty(editView1.getText())) {
            editView1.setError( "Model is required!" );


        } else if(TextUtils.isEmpty(editView2.getText())) {
            editView2.setError( "Year is required!" );

        } else {

            vehicle = StudentDB.getInstance().getstudentList().get(studentIndx).getVehicleid();
            vehicle.add(new Vehicle(editView0.getText().toString(), editView1.getText().toString(), Integer.valueOf(editView2.getText().toString())));
            StudentDB.getInstance().getstudentList().get(studentIndx).setVehicleid(vehicle);
            editView0.setEnabled(false);
            editView1.setEnabled(false);
            editView2.setEnabled(false);
            bt1.setVisibility(View.GONE);
            detailMenu.findItem(R.id.action_done).setVisible(true);
        }
    }



}
