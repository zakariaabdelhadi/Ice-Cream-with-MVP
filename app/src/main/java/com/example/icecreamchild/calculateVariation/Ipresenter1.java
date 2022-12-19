package com.example.icecreamchild.calculateVariation;


import java.util.List;

import GeneralInterfaces.IPresenterGeneral;
import entities.Station;

public interface Ipresenter1 extends IPresenterGeneral {

    interface OnFinishedListener  {
        // function to be called
        // once the Handler of Model class
        // completes its execution
        void onFinished(Station station);
        void onGettingNames(List<String> names);
        void onAddStationFinished();
    }

   // void setPresenter(Object presenter);



    void  addStation();
    void calculateVariance(int target, int actual);

    void onStationSelected(String name, String date);

    void getStationNames();
}




