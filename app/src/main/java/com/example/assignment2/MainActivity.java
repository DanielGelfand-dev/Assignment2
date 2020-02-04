package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
   // protected Button button;
    protected FloatingActionButton button;
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

    }
    public void showDialog(View v){
        FragmentManager manager = getSupportFragmentManager();
        myDialogClass myDialog = new myDialogClass();
        myDialog.show(manager,"CourseAddDialog");


    }
}
