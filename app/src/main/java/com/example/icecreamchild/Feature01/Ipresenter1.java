package com.example.icecreamchild.Feature01;


import java.util.List;

public interface Ipresenter1 {

    interface OnFinishedListener  {
        // function to be called
        // once the Handler of Model class
        // completes its execution
        void onFinished(Station station);
        void onGettingNames(List<String> names);
    }

    void setPresenter(Object presenter);

    void onDestroy();

    void onViewCreated();

    void calculateVariance(int target, int actual);

    void onStationSelected(String name, String date);

    void getStationNames();
}




