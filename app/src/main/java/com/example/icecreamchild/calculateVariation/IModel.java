package com.example.icecreamchild.calculateVariation;

import GeneralInterfaces.IModelGeneral;
import entities.Station;

public interface IModel extends IModelGeneral {


    void getStationNamesFromModel(Presenter1.OnFinishedListener onFinishedListener);


    void getSelectedStationByNameAndDate(Presenter1.OnFinishedListener onFinishedListener, String name, String date);

    void addStationInDB(Ipresenter1.OnFinishedListener listener,Station station);
}
