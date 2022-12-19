
package com.example.icecreamchild.calculateVariation;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import Utils.Utils;
import entities.Station;


public class Presenter1 implements Ipresenter1, Ipresenter1.OnFinishedListener {

    public static int COUNTER = 2;

    private static final int GREEN = 0;
    private static final int RED = 1;
    private static final int BLUE = 2;
    // the presenter interact only with the interface
    private Iview1 iview1;
    private IModel imodel1;


    // Dependency Injection
    // the presenter interact only with the interface
    public Presenter1(Iview1 iview1, IModel imodel1) {

        this.iview1 = iview1;
        this.imodel1 = imodel1;

    }


    @Override
    public void onDestroy() {

        this.iview1 = null;

    }

    @Override
    public void onViewCreated() {

        // load Station Data


    }

    @Override
    public void addStation() {
        Station station = new Station("Station " + COUNTER, "ID " + COUNTER, 80, "4-1-2022");
        COUNTER++;
        imodel1.addStationInDB(this,station);

    }

    @Override
    public void calculateVariance(int target, int actual) {


        if ((target - actual) > 10) {

            iview1.setVarianceAndColor((target - actual), RED);


        } else if ((actual - target) > 5) {

            iview1.setVarianceAndColor((actual - target), GREEN);


        } else {

            iview1.setVarianceAndColor((actual - target), BLUE);

        }


    }

    @Override
    public void onStationSelected(String name, String date) {

        if (!name.equals("") && !name.isEmpty()) {


            imodel1.getSelectedStationByNameAndDate(this, name, date);

        }

    }

    @Override
    public void getStationNames() {
        imodel1.getStationNamesFromModel(this);
    }


    public void onFinished(Station station) {
        if (iview1 != null && station != null) {


            iview1.DisplayStationData(station.getId(), station.getTarget());

            //   mainView.setString(string);
            //  mainView.hideProgress();
        } else {

            iview1.displayErrorMessage();
        }
    }

    @Override
    public void onGettingNames(List<String> names) {
        iview1.intializeStationslist(names);

    }

    @Override
    public void onAddStationFinished() {

    }


}
