package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class assignmentActivity extends AppCompatActivity {
    protected TextView text_title;
    protected TextView text_code;
    protected FloatingActionButton button;

    protected ListView listView;
    protected DatabaseHelper db;
    protected AssignmentListViewAdapter adapter;

   protected int courseID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Intent intent = getIntent();
        courseID= (intent.getIntExtra("CourseID",999))+1;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(getApplicationContext(),null,null);
        text_title = findViewById(R.id.textview_course_name_ass);
        text_code = findViewById(R.id.textview_course_code_ass);
        button = findViewById(R.id.dialog_button_ass);
        text_title.setText(db.getCOURSE(courseID).getTitle());
        text_code.setText(db.getCOURSE(courseID).getCode());

        listView = findViewById(R.id.listview_assignments);
        ArrayList<AssignmentClass> assignments = new ArrayList<>(); // this array will hold our courses
        assignments = db.getAllASSbyTAG(Integer.toString(courseID));



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(v);

            }
        });


        //set up adapter
        adapter = new AssignmentListViewAdapter(this,assignments);
        listView.setAdapter(adapter);


    }
    public void showDialog(View v){
        FragmentManager manager = getSupportFragmentManager();
        myAssDialogClass myDialog = new myAssDialogClass(courseID);
        myDialog.show(manager,"AssAddDialog");


    }
    @Override
    public boolean onSupportNavigateUp() {
       // Toast.makeText(profileActivity.this, "TESTING", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
      //  intent.putExtra("CourseID",position2);
        this.startActivity(intent);
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        //  intent.putExtra("CourseID",position2);
        this.startActivity(intent);
    }
}
