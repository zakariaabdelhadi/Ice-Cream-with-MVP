package com.example.icecreamchild.Feature01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
    private EditText actualEditText;
    private EditText dateEditText;


    private Button executeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        stationIdTextView = findViewById(R.id.text_view_StationID);
        targetTextView = findViewById(R.id.edit_text_Data_Target);
        actualEditText = findViewById(R.id.edit_text_Data_Actual);
        varianceTextView = findViewById(R.id.text_view_variance);
        dateEditText = findViewById(R.id.edit_text_Data);

        executeButton = findViewById(R.id.execute_button);
        executeButton.setEnabled(false);


        // kann in die presenterklasse gezogen werden
        setPresenter(new Presenter1(this, new ModelManager(this)));
        // ipresenter1.onViewCreated();

        autoCompleteTextView = findViewById(R.id.AutoCompleteTextview);
        //We will use this data to inflate the drop-down items
        ipresenter1.getStationNames();


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //    Toast.makeText(getApplicationContext(), "" + autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show();

                clearCurrentScreen();
                if(!dateEditText.getText().toString().equals("") && dateEditText.getText().toString() != null){
                    ipresenter1.onStationSelected(autoCompleteTextView.getText().toString(),dateEditText.getText().toString());



                }else{

                    Toast.makeText(getApplicationContext(), "Date muss eingegeben werden !", Toast.LENGTH_SHORT).show();



                }
            }
        });

        executeButton.setOnClickListener(
                v -> {


                    handleButtonClick();

                }

        );


        actualEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                handleTextChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                handleTextChanged();

            }
        });

        dateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                handleTextChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {
                handleTextChanged();

            }
        });


        // on below line we are adding click listener
        // for our pick date button
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheDate();
            }
        });


    }

    private void clearCurrentScreen() {

        stationIdTextView.setText("");
        targetTextView.setText("");
    }

    private void setTheDate() {
        // on below line we are getting
        // the instance of our calendar.
        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // on below line we are creating a variable for date picker dialog.
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our edit text.
                        dateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();
    }

    private void handleButtonClick() {
        if (actualEditText.getText() != null  && !actualEditText.getText().toString().equals("") && !dateEditText.getText().toString().equals("")) {

            ipresenter1.calculateVariance(Integer.valueOf(targetTextView.getText().toString()), Integer.valueOf(actualEditText.getText().toString()));


        } else {

            Toast.makeText(getApplicationContext(), "Actualvalue und Datum mÜssen eingegeben werden !", Toast.LENGTH_SHORT).show();

            varianceTextView.clearComposingText();

        }
    }

    private void handleTextChanged() {

        if (actualEditText.getText() != null && !actualEditText.getText().toString().equals("") && !dateEditText.getText().toString().equals("") && !targetTextView.getText().toString().equals("")) {

           ipresenter1.calculateVariance(Integer.valueOf(targetTextView.getText().toString()), Integer.valueOf(actualEditText.getText().toString()));


        } else {

            Toast.makeText(getApplicationContext(), "Actualvalue und Datum mÜssen eingegeben werden !", Toast.LENGTH_SHORT).show();
            varianceTextView.setText("");


        }
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
        switch (color) {

            case 0:
                varianceTextView.setTextColor(Color.parseColor("#008000"));
                break; // Green
            case 1:
                varianceTextView.setTextColor(Color.parseColor("#ff0000"));
                break; // RED
            case 2:
                varianceTextView.setTextColor(Color.parseColor("#0000ff"));
                break; // BLUE

        }


    }

   public void displayErrorMessage(){


       Toast.makeText(getApplicationContext(), "es gibt keine Daten an dem angegebenen Datum über die ausgewählte Station!", Toast.LENGTH_SHORT).show();



   }
    @Override
    public void onDestroy() {

        ipresenter1.onDestroy();
        super.onDestroy();

    }
}