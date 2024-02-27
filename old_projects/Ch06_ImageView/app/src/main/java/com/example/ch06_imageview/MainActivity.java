package com.example.ch06_imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    ImageView img;
    RadioGroup rg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=(ImageView) findViewById(R.id.imgOutput);
        rg = (RadioGroup) findViewById(R.id.rg_btns);
        rg.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.puppy:
                img.setImageResource(R.mipmap.dog);
                break;
            case R.id.elephant:
                img.setImageResource(R.mipmap.elephant);
                break;
            case R.id.rat:
                img.setImageResource(R.mipmap.mouse);
                break;
            case R.id.rabbit:
                img.setImageResource(R.mipmap.rabbit);
                break;
        }
    }
}