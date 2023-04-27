package com.example.ch4_4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText height;
    EditText weight;
    TextView output;
    String str_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height = (EditText) findViewById(R.id.height_input);
        weight = (EditText) findViewById(R.id.weight_input);
        output = (TextView) findViewById(R.id.txt_output);

        output.setTextColor(Color.RED);
        output.setVisibility(View.INVISIBLE);

        int int_height = Integer.parseInt(height.getText().toString());
        int int_weight = Integer.parseInt(weight.getText().toString());
        float result;
        result = int_weight / ((int_height / 100) * (int_height / 100));
        str_result = String.valueOf(result);

    }


    public void send_btn_click(View view) {
        output.setText(str_result);
        output.setVisibility(View.VISIBLE);
    }

    public void clear_btn_click(View view) {
        output.setVisibility(View.INVISIBLE);
        height.setText("");
        weight.setText("");
    }
}