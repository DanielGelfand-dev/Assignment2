package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "courses.db";
    static private final int DATABSE_VERSION = 1;
    public static final String TABLE_COURSE_NAME = "course_table";
    public static final String TABLE_ASS_NAME = "ass_table";
    public static final String COURSE_ROW0 = "ID";
    public static final String COURSE_ROW1 = "CourseID";
    public static final String COURSE_ROW2 = "CourseTitle";
    public static final String COURSE_ROW3 = "CourseCode";

    public static final String ASS_ROW1 = "AssignmentID";
    public static final String ASS_ROW2 = "CourseID";
    public static final String ASS_ROW3 = "AssTitle";
    public static final String ASS_ROW4 = "Grade";


    private static final String CREATE_TABLE_COURSES = "CREATE TABLE "
            + TABLE_COURSE_NAME + "(" +
            COURSE_ROW1 + " INTEGER PRIMARY KEY," +
            COURSE_ROW2 + " TEXT," +
            COURSE_ROW3 + " TEXT" + ")";
    private static final String CREATE_TABLE_ASSIGNMENTS = "CREATE TABLE "
            + TABLE_ASS_NAME + "(" +
            ASS_ROW1 + " INTEGER PRIMARY KEY," +
            ASS_ROW2 + " TEXT," +
            ASS_ROW3 + " TEXT," +
            ASS_ROW4 + " TEXT" + ")";

    private static final String REINDEX_TABLE_COURSES = "CREATE TRIGGER REINDEX AFTER DELETE ON course_table FOR EACH ROW BEGIN UPDATE course_table SET  = old.ID - 1 WHERE ID > old.ID; END;";


    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_COURSES);
        db.execSQL(CREATE_TABLE_ASSIGNMENTS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//if we upgrade delete old tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASS_NAME);
//remake the tables
        onCreate(db);


        //CREATING TABLES


    }

    public long createCOURSE(CoursesClass course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //  System.out.println("getCourseID gets us : "+course.getId());


        //  values.put(COURSE_ROW1,course.getId());
        values.put(COURSE_ROW2, course.getTitle());
        values.put(COURSE_ROW3, course.getCode());

        long course_id = db.insert(TABLE_COURSE_NAME, null, values);
//        Log.d("DB LOG", "COURSE MADE");


        return course_id;

    }

    public CoursesClass getCOURSE(long course_id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE_NAME + " WHERE " + COURSE_ROW1 + " = " + course_id;

//        Log.d("DB LOG", selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) c.moveToFirst();
        CoursesClass course = new CoursesClass();


        course.setId(c.getInt(c.getColumnIndex(COURSE_ROW1)));
        //course.setCourseID(c.getInt(c.getColumnIndex(COURSE_ROW1)));
        course.setTitle(c.getString(c.getColumnIndex(COURSE_ROW2)));
        course.setCode(c.getString(c.getColumnIndex(COURSE_ROW3)));

        return course;


    }

    public ArrayList<CoursesClass> getAllCOURSE() {
        ArrayList<CoursesClass> courses = new ArrayList<CoursesClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE_NAME;
//        Log.e("DB LOG", selectQuery );

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                CoursesClass course = new CoursesClass();

                //      course.setCourseID(c.getInt((c.getColumnIndex(COURSE_ROW0))));
                course.setId(c.getInt((c.getColumnIndex(COURSE_ROW1))));

                course.setTitle(c.getString((c.getColumnIndex(COURSE_ROW2))));
                course.setCode(c.getString((c.getColumnIndex(COURSE_ROW3))));
                courses.add(course);
            } while (c.moveToNext());
        }
        return courses;
    }

    public List<CoursesClass> getAllCOURSEbyTAG(String tag_id) {
        List<CoursesClass> courses = new ArrayList<CoursesClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE_NAME +
                " WHERE " + COURSE_ROW1 + " = '" + tag_id + "'";
//        Log.e("DB LOG", selectQuery );

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                CoursesClass course = new CoursesClass();
                course.setId(c.getInt((c.getColumnIndex(COURSE_ROW1))));
                course.setTitle(c.getString((c.getColumnIndex(COURSE_ROW2))));
                course.setCode(c.getString((c.getColumnIndex(COURSE_ROW3))));
                courses.add(course);
            } while (c.moveToNext());
        }
        return courses;
    }


    public int getCOURSEcount() {
        String countQuery = "SELECT * FROM " + TABLE_COURSE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(countQuery, null);
        int count = c.getCount();
        c.close();
        return count;
    }

    public void deleteCOURSE(long course_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE_NAME, COURSE_ROW1 + " = ?", new String[]{String.valueOf(course_id)});
        deleteASS(course_id);
    }

    public boolean getexistsCOURSE(long course_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE_NAME + " WHERE " + COURSE_ROW1 + " = " + course_id;
        //  Log.d("DATABASE", "getexistsCOURSE: COURSEID : "+course_id);
        Cursor c = db.rawQuery(selectQuery, null);
        boolean exists = false;
        int count = c.getCount();
        // Log.d("DATABASE", "getexistsCOURSE: COUNT : "+count);

        if (count > 0) {
            exists = true;
        } else if (count <= 0) {
            exists = false;
        }
        c.close();
        return exists;
    }


    public long createASS(AssignmentClass ass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(ASS_ROW1, ass.getId());
        values.put(ASS_ROW2, ass.getCourseid());
        values.put(ASS_ROW3, ass.getName());
        values.put(ASS_ROW4, ass.getGrade());

        long ass_id = db.insert(TABLE_ASS_NAME, null, values);


        return ass_id;

    }

    public void reindexCOURSES() {
        // System.out.println("Reindex called");

        String selectQuery = "SELECT * FROM " + TABLE_COURSE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Cursor c = db.rawQuery(selectQuery, null);

        for (int i = 1; i < getCOURSEcount(); i++) {
            if (c.moveToFirst()) {
                do {
                    db.execSQL("UPDATE " + TABLE_COURSE_NAME + " SET " + COURSE_ROW1 + " = " + i + " WHERE " + COURSE_ROW1 + " >= " + i + ";");

                    // System.out.println("Reindex" + i);
                    // db.update(TABLE_COURSE_NAME, values, COURSE_ROW0 +" = ?", null);
                } while (c.moveToNext());


            }


        }


    }


    public ArrayList<AssignmentClass> getAllASS() {
        ArrayList<AssignmentClass> asses = new ArrayList<AssignmentClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_ASS_NAME;
        //  Log.e("DB LOG", selectQuery );

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                AssignmentClass ass = new AssignmentClass();
                ass.setId(c.getInt((c.getColumnIndex(ASS_ROW1))));
                ass.setCourseid(c.getInt((c.getColumnIndex(ASS_ROW2))));
                ass.setName(c.getString((c.getColumnIndex(ASS_ROW3))));
                ass.setGrade(c.getInt((c.getColumnIndex(ASS_ROW4))));

                asses.add(ass);
            } while (c.moveToNext());
        }
        c.close();
        return asses;
    }


    public ArrayList<AssignmentClass> getAllASSbyTAG(long course_id) {
        ArrayList<AssignmentClass> asses = new ArrayList<AssignmentClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_ASS_NAME +
                " WHERE " + ASS_ROW2 + " = '" + course_id + "'";
//        Log.e("DB LOG", selectQuery );

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                AssignmentClass ass = new AssignmentClass();
                ass.setId(c.getInt((c.getColumnIndex(ASS_ROW1))));
                ass.setCourseid(c.getInt((c.getColumnIndex(ASS_ROW2))));
                ass.setName(c.getString((c.getColumnIndex(ASS_ROW3))));
                ass.setGrade(c.getInt((c.getColumnIndex(ASS_ROW4))));
                asses.add(ass);
            } while (c.moveToNext());
        }
        c.close();
        return asses;
    }

    public int averagegradeASS(long course_id) {
        int average = 0;
        ArrayList<AssignmentClass> asses = new ArrayList<AssignmentClass>();
        asses = getAllASSbyTAG(course_id);
        int sum = 0;
        for (int i = 0; i < asses.size(); i++) {

            sum = sum + asses.get(i).getGrade();

        }
//        Log.d("ASSIGNMENT SIZE", "averagegradeASS: "+asses.size());
        if (asses.size() == 0) {
            average = -1;
        }
        if (asses.size() > 0) {
            average = sum / (asses.size());
        }

        return average;
    }

    public int averageOFALLgradeASS() {
        int average = 0;
        ArrayList<AssignmentClass> asses = new ArrayList<AssignmentClass>();
        asses = getAllASS();
        int sum = 0;
        for (int i = 0; i < getASScount(); i++) {

            sum = sum + asses.get(i).getGrade();

        }
        if (asses.size() == 0) {
            average = -1;
        }
        if (getASScount() > 0) {
            average = sum / asses.size();
        }


        return average;
    }


    public int getASScount(long course_id) {
        String countQuery = "SELECT * FROM " + TABLE_ASS_NAME + " WHERE " + COURSE_ROW1 + " = " + course_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(countQuery, null);
        int count = c.getCount();
        c.close();
        return count;
    }


    public int getASScount() {
        String countQuery = "SELECT * FROM " + TABLE_ASS_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(countQuery, null);
        int count = c.getCount();
        c.close();
        return count;
    }

    public void deleteASS(long course_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ASS_NAME, ASS_ROW2 + " = ?", new String[]{String.valueOf(course_id)});
    }


}
