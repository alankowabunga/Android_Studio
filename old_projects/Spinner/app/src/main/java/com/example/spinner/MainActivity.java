package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView output;
    Spinner spinner;
    Boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.output);
        output.setVisibility(View.INVISIBLE);

        spinner = (Spinner) findViewById(R.id.spinner);
        // 預設選項為空值
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (firstTime) {
                    firstTime = false;
                } else {

                    // 取值
                    String result = parent.getItemAtPosition(position).toString();
                    // Toast
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    // output
                    output.setText(result);
                    output.setVisibility(View.VISIBLE);

                    // switch
                    switch (result) {
                        case "C 250 coupe":
                            output.setTextColor(Color.BLUE);
                            break;
                        case "Q 60":
                            output.setTextColor(Color.GREEN);
                            break;
                        case "430i coupe":
                            output.setTextColor(Color.RED);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}