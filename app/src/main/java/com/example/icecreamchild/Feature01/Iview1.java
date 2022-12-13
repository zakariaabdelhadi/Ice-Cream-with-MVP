package com.example.icecreamchild.Feature01;

import java.util.List;

public interface Iview1 {


    void setPresenter(Ipresenter1 presenter);

    void DisplayStationData(String StationID, int target);

    void intializeStationslist(List<String> names);

    void setVarianceAndColor(int i, int i1);
}
