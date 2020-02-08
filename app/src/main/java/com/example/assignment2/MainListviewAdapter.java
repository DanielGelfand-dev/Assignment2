package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainListviewAdapter extends BaseAdapter {

    Context con;
    ArrayList<CoursesClass> array;

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
    public View getView(int position, View convertView, ViewGroup parent) {
       final int position2= position;
        convertView = LayoutInflater.from(con).inflate(R.layout.listview_courses_layout,parent,false);

        //TextView num = convertView.findViewById(R.id.);
        TextView coursename = convertView.findViewById(R.id.textview_course_name);
        TextView coursecode = convertView.findViewById((R.id.textview_course_code));
        TextView average = convertView.findViewById(R.id.textview_course_average);

        // set data into textview...
        coursename.setText(array.get(position).getTitle());
        coursecode.setText(array.get(position).getCode());
     //   average.setText((array.get(position).getAverage()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(con,assignmentActivity.class);
                intent.putExtra("CourseID",position2);
                con.startActivity(intent);
            }
        });






        return convertView;
    }
}


