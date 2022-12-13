
package com.example.icecreamchild.Feature01;

import java.util.List;

public class Presenter1 implements Ipresenter1,IModel.OnFinishedListener{


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
    public void setPresenter(Object presenter) {

    }

    @Override
    public void onDestroy() {

        this.iview1 = null ;

    }

    @Override
    public void onViewCreated() {

        // load Station Data



    }

    @Override
    public void calculateVariance(int target, int actual) {



        if  ((target - actual) > 10) iview1.setVarianceAndColor((target - actual), RED);

        if ((actual - target)  > 5) iview1.setVarianceAndColor((actual - target),GREEN);

        if((target - actual) < 10  || (actual - target)  > 5) iview1.setVarianceAndColor((actual - target),BLUE);

    }

    @Override
    public void onStationSelected( String name ) {

        if(!name.equals("") && !name.isEmpty()){


            imodel1.getSelectedStationByName(this,name);

        }

    }

    @Override
    public void getStationNames() {
        imodel1.getStationNamesFromModel(this);
    }


    public void onFinished(Station station) {
        if (iview1 != null) {


            iview1.DisplayStationData(station.getId(),station.getTarget());

            //   mainView.setString(string);
          //  mainView.hideProgress();
        }
    }

    @Override
    public void onGettingNames(List<String> names) {
        iview1.intializeStationslist(names);

    }


}
