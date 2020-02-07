package com.example.assignment2;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class myDialogClass extends DialogFragment {
protected Button savebutton;
protected Button cancelbutton;
protected EditText name,code;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_course,container,false);
        savebutton =  view.findViewById(R.id.dialog_save);
        cancelbutton = view.findViewById(R.id.dialog_cancel);
        name = view.findViewById(R.id.dialog_name);
        code = view.findViewById(R.id.dialog_code);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(getActivity(),null,null);
                CoursesClass course = new CoursesClass();
                if (view.getId()== R.id.dialog_save) {
                    Toast.makeText(getActivity(),"CLICKED SAVE",Toast.LENGTH_SHORT).show();
                    Log.d("ONCLICK", "dialog save: ");
                    String coursename = name.getText().toString();
                    String coursecode = code.getText().toString();
                    course.setTitle(coursename);
                    course.setCode(coursecode);
                    db.createCOURSE(course);
                }
                else
                {
                    db.close();
                }
            }
        });



        return view;
    }


}
