package com.example.coursfsr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseFav extends SQLiteOpenHelper {
    public static final String BDname = "favorite.db";

    public DataBaseFav(Context context) {
        super(context, BDname, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table favorite ( id INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT,imageInt INTEGER,numCours INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS favorite");
        onCreate(db);
    }

    //interaction sur Base de Donnees
    public void Insert_to_favorite(String Title, int imageInt, int numCours) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", Title);
        contentValues.put("imageInt", imageInt);
        contentValues.put("numCours",numCours);
        //long result =
        db.insert("favorite", null, contentValues);
        /*
        if (result == -1)
            return false;
        else
            return true;
        */
    }

    public ArrayList get_All_Favorite() {
        ArrayList<Cours> arraylist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from favorite", null);
        rs.moveToFirst();
        while (!rs.isAfterLast()) {
            //int id = rs.getInt(rs.getColumnIndex("id"));
            String Title = rs.getString(rs.getColumnIndex("Title"));
            int imageInt = rs.getInt(rs.getColumnIndex("imageInt"));
            int numCours = rs.getInt(rs.getColumnIndex("numCours"));
            arraylist.add(new Cours(Title,imageInt,numCours));
            rs.moveToNext();
        }
        return arraylist;
    }

    public int get_numCours() {
        SQLiteDatabase db = this.getReadableDatabase();
        return 0;
    }

    public int get_check_List_Favorite(String Title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from favorite Where Title Like'" + Title + "'", null);
        int count = rs.getCount();
        return count;
    }

    public Integer Delete(String Titre) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("favorite", "Title = ?", new String[]{Titre});
    }
}
