package com.example.icecreamchild.Feature01;


public interface Ipresenter1 {

    void setPresenter(Object presenter);

    void onDestroy();

    void onViewCreated();

    void calculateVariance(int target, int actual);

    void onStationSelected(String name);

    void getStationNames();
}




