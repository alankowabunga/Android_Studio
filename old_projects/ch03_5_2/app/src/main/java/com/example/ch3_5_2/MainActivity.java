package com.example.ch3_5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switch_Btn(View view){
//        取得 EditText元件
        EditText txtTemp=(EditText) findViewById(R.id.txtTemp);
        int tmp=Integer.parseInt(txtTemp.getText().toString()); //  如果輸入的是浮點數，則用 double
        double result = (tmp*9.0)/5.0+32.0;

//        取得 TextView元件
        TextView output=(TextView) findViewById(R.id.lblOutput);
        output.setText("華氏溫度:"+ result);
    }
}