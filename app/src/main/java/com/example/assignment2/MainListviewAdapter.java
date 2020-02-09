package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainListviewAdapter extends BaseAdapter {

    Context con;
    ArrayList<CoursesClass> array;
    protected DatabaseHelper db;
    protected ListView listView;
    // create constructor


    public MainListviewAdapter(Context con, ArrayList<CoursesClass> array) {
        this.con = con;
        this.array = array;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int position2 = position;

        convertView = LayoutInflater.from(con).inflate(R.layout.listview_courses_layout, parent, false);

        //TextView num = convertView.findViewById(R.id.);
        TextView coursename = convertView.findViewById(R.id.textview_course_name);
        TextView coursecode = convertView.findViewById((R.id.textview_course_code));
        TextView average = convertView.findViewById(R.id.textview_course_average);
        listView = parent.findViewById(R.id.courses_listView);

        // set data into textview...
        coursename.setText(array.get(position).getTitle());
        coursecode.setText(array.get(position).getCode());


        db = new DatabaseHelper(con, null, null);
//        System.out.println("OUR average for this courseid"+db.averagegradeASS(1));
        if (db.averagegradeASS(array.get(position).getId()) >= 0) {
            average.setText("Assignment Average: " + String.valueOf(db.averagegradeASS(array.get(position).getId())) + " %");
        }
        if (db.averagegradeASS(array.get(position).getId()) < 0) {
            average.setText("Assignment Average: Na");
        }
        //System.out.println("COURSEID --->"+array.get(position).getCourseID());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int courseid = array.get(position).getId();

//                System.out.println("COURSEID --->"+array.get(position).getId());
                Intent intent = new Intent(con, assignmentActivity.class);
                intent.putExtra("CourseID", courseid);

                con.startActivity(intent);
            }
        });


        return convertView;
    }
}


