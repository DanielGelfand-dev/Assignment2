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

public class myAssDialogClass extends DialogFragment {
    protected Button savebutton;
    protected Button cancelbutton;
    protected EditText name,grade;
    protected int courseID;


    public myAssDialogClass(int gottencourseID) {
        this.courseID = gottencourseID;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_assign,container,false);
        savebutton =  view.findViewById(R.id.dialog_save_ass);
        cancelbutton = view.findViewById(R.id.dialog_cancel_ass);
        name = view.findViewById(R.id.dialog_name_ass);
        grade = view.findViewById(R.id.dialog_grade_ass);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(getActivity(),null,null);
                AssignmentClass ass = new AssignmentClass();
                if (view.getId()== R.id.dialog_save_ass) {
                    Toast.makeText(getActivity(),"CLICKED SAVE",Toast.LENGTH_SHORT).show();
                    Log.d("ONCLICK", "dialog save: ");
                    String assname = name.getText().toString();
                    String assgrade = grade.getText().toString();
                    ass.setName(assname);
                    ass.setGrade(Integer.parseInt(assgrade));
                    ass.setCourseid(courseID);
                    db.createASS(ass);
                    Log.d("ONCLICK", "onClick: created ass ");
                    getActivity().startActivityForResult(getActivity().getIntent(),10);
                    dismiss();
                }
                else if (view.getId()== R.id.dialog_cancel_ass) {
                    db.close();
                    Toast.makeText(getActivity(),"CANCELLED",Toast.LENGTH_SHORT).show();
                    getActivity().startActivityForResult(getActivity().getIntent(),10);
                    dismiss();

                }
            }
        });



        return view;
    }


}
