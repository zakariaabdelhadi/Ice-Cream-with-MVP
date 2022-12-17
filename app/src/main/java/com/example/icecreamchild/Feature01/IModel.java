package com.example.icecreamchild.Feature01;

import java.util.List;

public interface IModel {


    void getStationNamesFromModel(Presenter1.OnFinishedListener onFinishedListener);


    void getSelectedStationByNameAndDate(Presenter1.OnFinishedListener onFinishedListener, String name, String date);

}
