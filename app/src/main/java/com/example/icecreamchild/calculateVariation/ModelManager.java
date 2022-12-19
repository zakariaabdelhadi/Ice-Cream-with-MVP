package com.example.icecreamchild.calculateVariation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import entities.Station;

public class ModelManager extends SQLiteOpenHelper implements IModel {

    private static final String DB_Name = "stations";
    private static final int VERSION = 3;



    public ModelManager(@Nullable Context context) {
        super(context, DB_Name, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String query = "create table Station ( id text primary key , name text,target integer,date text)";
        // sqLiteDatabase.execSQL(query);

        String query2 = "insert  into Station (id,name,target,date) values ('b70','BRB 14770',50,'01-01-2022') ";
        String query3 = "insert  into Station (id,name,target,date) values ('b72','BRB 14772',79,'01-02-2022') ";
        String query4 = "insert  into Station (id,name,target,date) values ('b76','BRB 14776',67,'01-03-2022') ";

//        sqLiteDatabase.execSQL(query2);
//        sqLiteDatabase.execSQL(query3);
//        sqLiteDatabase.execSQL(query4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String strSQL = "UPDATE Station SET date ='1-1-2022' WHERE id = 'b70'";
        String strSQL2 = "UPDATE Station SET  date='2-1-2022' WHERE id = 'b72'";
        String strSQL3 = "UPDATE Station SET target = 48 WHERE id = 'b74'";
        String strSQL4 = "UPDATE Station SET date='3-1-2022' WHERE id = 'b76'";


        sqLiteDatabase.execSQL(strSQL);
        sqLiteDatabase.execSQL(strSQL2);
        //sqLiteDatabase.execSQL(strSQL3);
        sqLiteDatabase.execSQL(strSQL4);


        // String query5 = "insert  into Station (id,name,target) values ('b74','BRB 14774',10) ";

        // sqLiteDatabase.execSQL(query5);
//
//        sqLiteDatabase.execSQL("delete from Station");
//
//        sqLiteDatabase.execSQL("ALTER TABLE Station ADD COLUMN date text DEFAULT '00-00-00'");

        String query2 = "insert  into Station (id,name,target,date) values ('b70','BRB 14770',50,'01-01-2022') ";
        String query3 = "insert  into Station (id,name,target,date) values ('b72','BRB 14772',79,'01-02-2022') ";
        String query4 = "insert  into Station (id,name,target,date) values ('b76','BRB 14776',67,'01-03-2022') ";

        // sqLiteDatabase.execSQL(query);
//        sqLiteDatabase.execSQL(query2);
//        sqLiteDatabase.execSQL(query3);
//        sqLiteDatabase.execSQL(query4);


    }


    public Station getStationByNameAndDate(String name, String date) {

        Station station = null;
        String query = "select * from Station where name='" + name + "' and date = '" + date + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            station = new Station(cursor.getString(1), cursor.getString(0), cursor.getInt(2), cursor.getString(3));
            cursor.moveToNext();

        }
        cursor.close();


        return station;
    }

    public List<String> getStationNames(List<String> names) {

        // List<String> names = new ArrayList<String>();
        String query = "select name from Station";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            names.add(cursor.getString(0));
            //  station = new Station(cursor.getString(1), cursor.getString(0), cursor.getInt(2));
            cursor.moveToNext();

        }
        cursor.close();


        return names;
    }

    @Override
    public void getStationNamesFromModel(final Presenter1.OnFinishedListener listener) {

        List<String> names = new ArrayList<String>();
        names = getStationNames(names);

        List<String> finalNames = names;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onGettingNames(finalNames);
            }
        }, 1200);


    }

    @Override
    public void getSelectedStationByNameAndDate(final Ipresenter1.OnFinishedListener listener, String name, String date) {

        Station station = getStationByNameAndDate(name, date);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(station);
            }
        }, 1200);


    }

    @Override
    public void addStationInDB(final Presenter1.OnFinishedListener listener, Station station) {

        String query= "insert  into Station (id,name,target,date) values ('"+station.getId()+"','"+station.getName()+"',"+station.getTarget()+",'"+station.getDate()+"') ";
        this.getWritableDatabase().execSQL(query);

        List<String> names = new ArrayList<String>();
        names = getStationNames(names);

        List<String> finalNames = names;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onGettingNames(finalNames);
            }
        }, 1200);






    }
}
