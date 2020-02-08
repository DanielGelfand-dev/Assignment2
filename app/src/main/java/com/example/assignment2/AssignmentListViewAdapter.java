package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AssignmentListViewAdapter extends BaseAdapter {
    Context con;
    ArrayList<AssignmentClass> array;

    public AssignmentListViewAdapter(Context con, ArrayList<AssignmentClass> array) {
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
        final int position2 = position;
        convertView = LayoutInflater.from(con).inflate(R.layout.listview_assignment_layout, parent, false);

        //TextView num = convertView.findViewById(R.id.);
        TextView assignmentname = convertView.findViewById(R.id.textview_ass_name);
        TextView assignmentgrade = convertView.findViewById((R.id.textview_ass_grade));
       // TextView average = convertView.findViewById(R.id.textview_course_average);

        // set data into textview...
        assignmentname.setText(array.get(position).getName());
        assignmentgrade.setText(array.get(position).getGrade()+" %");
        //   average.setText((array.get(position).getAverage()));

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(con, assignmentActivity.class);
//                intent.putExtra("CourseID", position2);
//                con.startActivity(intent);
//            }
//        });
        return convertView;
    }
}