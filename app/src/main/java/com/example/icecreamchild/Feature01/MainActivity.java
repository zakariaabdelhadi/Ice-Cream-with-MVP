package com.example.icecreamchild.Feature01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.icecreamchild.R;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Iview1 {

    // communication throw the interface
    private Ipresenter1 ipresenter1;


    List<String> stationNames = new ArrayList<String>();
    //String[] stationNames;
    AutoCompleteTextView autoCompleteTextView;

    //ModelManager modelManager = new ModelManager(this);

    private TextView stationIdTextView;
    private TextView targetTextView;
    private TextView varianceTextView;
    private EditText actualTextView;

    private Button executeButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        stationIdTextView = findViewById(R.id.text_view_StationID);
        targetTextView = findViewById(R.id.edit_text_Data_Target);
        actualTextView = findViewById(R.id.edit_text_Data_Actual);
        varianceTextView = findViewById(R.id.text_view_variance);

        executeButton =  findViewById(R.id.execute_button);
        executeButton.setEnabled(false);


        //  setPresenter(new Presenter1(this,new Station()));
        setPresenter(new Presenter1(this, new ModelManager(this)));

        // ipresenter1.onViewCreated();

        autoCompleteTextView = findViewById(R.id.AutoCompleteTextview);
        //We will use this data to inflate the drop-down items
        ipresenter1.getStationNames();


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "" + autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show();
                ipresenter1.onStationSelected(autoCompleteTextView.getText().toString());
            }
        });

        executeButton.setOnClickListener(
                v -> {


                    if(actualTextView.getText() != null){

                        ipresenter1.calculateVariance(Integer.valueOf(targetTextView.getText().toString()), Integer.valueOf(actualTextView.getText().toString()));


                    }else{

                        Toast.makeText(getApplicationContext(), "Actualvalue muss eingegeben werden !", Toast.LENGTH_SHORT).show();

                        varianceTextView.clearComposingText();

                    }

                }

        );


        actualTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(actualTextView.getText() != null && !actualTextView.getText().toString().equals("")){

                    ipresenter1.calculateVariance(Integer.valueOf(targetTextView.getText().toString()), Integer.valueOf(actualTextView.getText().toString()));


                }else{

                    Toast.makeText(getApplicationContext(), "Actualvalue muss eingegeben werden !", Toast.LENGTH_SHORT).show();
                    varianceTextView.setText("");


                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(actualTextView.getText() != null && !actualTextView.getText().toString().equals("")){
                    ipresenter1.calculateVariance(Integer.valueOf(targetTextView.getText().toString()), Integer.valueOf(actualTextView.getText().toString()));


                }else{

                    Toast.makeText(getApplicationContext(), "Actualvalue muss eingegeben werden !", Toast.LENGTH_SHORT).show();
                    varianceTextView.setText("");


                }
            }
        });


    }


    @Override
    public void setPresenter(Ipresenter1 presenter) {

        this.ipresenter1 = presenter;

    }

    @Override
    public void DisplayStationData(String stationId, int target) {

        stationIdTextView.setText(stationId);
        targetTextView.setText(String.valueOf(target));
        executeButton.setEnabled(true);


    }

    @Override
    public void intializeStationslist(List<String> names) {

        stationNames = names;
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_item, stationNames);
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    public void setVarianceAndColor(int variance, int color) {

        varianceTextView.setText(String.valueOf(variance));
        switch (color){

            case 0:         varianceTextView.setTextColor(Color.parseColor("#008000"));break; // Green
            case 1:         varianceTextView.setTextColor(Color.parseColor("#ff0000"));break; // RED
            case 2:        varianceTextView.setTextColor(Color.parseColor("#0000ff"));break; // BLUE

        }


    }


    @Override
    public void onDestroy() {

        ipresenter1.onDestroy();
        super.onDestroy();

    }
}