package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   // protected Button button;
    protected FloatingActionButton button;
    protected ListView listView;
    protected DatabaseHelper db;
    protected MainListviewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.dialog_button);

         button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          showDialog(v);

        }
    });

        db = new DatabaseHelper(getApplicationContext(),null,null);

         //set up listview
        listView = findViewById(R.id.courses_listView);
        ArrayList<CoursesClass> courses = new ArrayList<>(); // this array will hold our courses
        for(int i = 1; i<=db.getCOURSEcount();i++)
        {
            courses.add(db.getCOURSE(i));

        }



        //set up adapter
        adapter = new MainListviewAdapter(this,courses);
        listView.setAdapter(adapter);




    }
    public void showDialog(View v){
        FragmentManager manager = getSupportFragmentManager();
        myDialogClass myDialog = new myDialogClass();
        myDialog.show(manager,"CourseAddDialog");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RESUMED", "onResume: ");
        ArrayList<CoursesClass> courses = new ArrayList<>(); // this array will hold our courses

        for(int i = 1; i<=db.getCOURSEcount();i++)
        {
            courses.add(db.getCOURSE(i));

        }
        adapter = new MainListviewAdapter(this,courses);
        listView.setAdapter(adapter);

    }
}
