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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // protected Button button;
    protected FloatingActionButton button;
    protected ListView listView;
    protected DatabaseHelper db;
    protected MainListviewAdapter adapter;
    protected TextView averageOfAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.dialog_button);
        averageOfAll = findViewById(R.id.allcourseaverage_textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(v);

            }
        });

        db = new DatabaseHelper(getApplicationContext(), null, null);

        //set up listview
        listView = findViewById(R.id.courses_listView);
        ArrayList<CoursesClass> courses = new ArrayList<>(); // this array will hold our courses
        int courseCount = db.getCOURSEcount() + 1;

//        for(int i = 1; i<=courseCount;i++)
//        {
//            if (db.getexistsCOURSE(i) == true){
//                courses.add(db.getCOURSE(i));
//              //  System.out.println("course exists so I = " + i);
//            }
//            if(db.getexistsCOURSE(i) == false) {
//             //   System.out.println("course DOESNT exists so while I = " + i);
//                 while(db.getexistsCOURSE(i) == false && i<courseCount)
//                {
//                    i++;
//                    // System.out.println("Course doesn't exist so I = " + i);
//                    if(db.getexistsCOURSE(i) == true){
//                        courseCount=courseCount+i;
//                        courses.add(db.getCOURSE(i));
//                     //   System.out.println("COURSE EXISTS AGAIN SO NEW I = "+i+" AND courseCount is now : "+courseCount);
//
//                    }
//
//
//                }
//
//
//
//            }
//           // System.out.println("Reached end of for loop so I = " + i);
//
//        }
        courses = db.getAllCOURSE();
        CoursesClass course = new CoursesClass();
        for (int i = 0; i < db.getCOURSEcount(); i++) {

            course = courses.get(i);
            //listView.setTag(i,course);

        }

        //set up adapter
        adapter = new MainListviewAdapter(this, courses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //  Log.d("tag", "onItemClick: IT HAPPENEDDD");

                // Object o = listView.getItemAtPosition(position);

            }
        });


    }

    public void showDialog(View v) {
        FragmentManager manager = getSupportFragmentManager();
        myDialogClass myDialog = new myDialogClass();
        myDialog.show(manager, "CourseAddDialog");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RESUMED", "onResume: ");

        if (db.averageOFALLgradeASS() >= 0)
            averageOfAll.setText("Average Grade of ALL Assignments: " + db.averageOFALLgradeASS() + " %");
        if (db.averageOFALLgradeASS() < 0)
            averageOfAll.setText("Average Grade of ALL Assignments: NA");


        ArrayList<CoursesClass> courses = new ArrayList<>(); // this array will hold our courses
        int courseCount = db.getCOURSEcount() + 1;
//        for(int i = 1; i<=courseCount;i++)
//        {
//            if (db.getexistsCOURSE(i) == true){
//                courses.add(db.getCOURSE(i));
//                listView.setTag();
//             //   System.out.println("course exists so I = " + i);
//            }
//            if(db.getexistsCOURSE(i) == false) {
//               // System.out.println("course DOESNT exists so while I = " + i);
//                while(db.getexistsCOURSE(i) == false && i<courseCount)
//                {
//                    i++;
//                    // System.out.println("Course doesn't exist so I = " + i);
//                    if(db.getexistsCOURSE(i) == true){
//                        courseCount=courseCount+i;
//                        courses.add(db.getCOURSE(i));
//                       // System.out.println("COURSE EXISTS AGAIN SO NEW I = "+i+" AND courseCount is now : "+courseCount);
//
//                    }
//
//
//                }
//
//
//
//            }
//          //  System.out.println("Reached end of for loop so I = " + i);
//
//        }

        courses = db.getAllCOURSE();
        adapter = new MainListviewAdapter(this, courses);
        listView.setAdapter(adapter);


    }

    @Override
    public boolean onSupportNavigateUp() {
        // Toast.makeText(profileActivity.this, "TESTING", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        //  intent.putExtra("CourseID",position2);
        this.startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        //  intent.putExtra("CourseID",position2);
        this.startActivity(intent);
    }
}
