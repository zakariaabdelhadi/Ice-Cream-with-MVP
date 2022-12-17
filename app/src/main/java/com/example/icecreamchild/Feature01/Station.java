package com.example.icecreamchild.Feature01;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Station {


    private String name;
    private String id;
    private  int Target;
    private String date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getTarget() {
        return Target;
    }

    public void setTarget(int target) {
        Target = target;
    }


    public Station(String name, String id, int target, String date) {
        this.name = name;
        this.id = id;
        this.Target = target;
        this.date = date;
    }

}
