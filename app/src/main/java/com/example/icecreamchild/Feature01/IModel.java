package com.example.icecreamchild.Feature01;

import java.util.List;

public interface IModel {


    void getStationNamesFromModel(IModel.OnFinishedListener onFinishedListener);

    interface OnFinishedListener  {
        // function to be called
        // once the Handler of Model class
        // completes its execution
        void onFinished(Station station);
        void onGettingNames(List<String> names);
    }

    void getSelectedStationByName(IModel.OnFinishedListener onFinishedListener, String name);

}
