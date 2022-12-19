package com.example.icecreamchild.calculateVariation;

import java.util.List;

import GeneralInterfaces.IViewGeneral;

public interface Iview1 extends IViewGeneral {



    void DisplayStationData(String StationID, int target);

    void intializeStationslist(List<String> names);

    void setVarianceAndColor(int i, int i1);

    void displayErrorMessage();
}
