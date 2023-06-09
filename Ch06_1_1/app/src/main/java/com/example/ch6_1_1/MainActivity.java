package com.example.ch6_1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_Click(View view) {
        String str="";
//        取得性別
//        RadioButton 使用 isChecked() 方法
        RadioButton boy=(RadioButton) findViewById(R.id.male_Btn);
        if(boy.isChecked()){
            str+="男\n";
        }
        RadioButton girl=(RadioButton) findViewById(R.id.female_Btn);
        if(girl.isChecked()){
            str+="女\n";
        }

//        取得門票種類
//        RadioGroup 使用 getCheckedRadioButtonId() 方法
        RadioGroup type=(RadioGroup) findViewById(R.id.rgType);
        if(type.getCheckedRadioButtonId()==R.id.adult_Ticket){
            str+="全票\n";
        } else if (type.getCheckedRadioButtonId()==R.id.kid_Ticket) {
            str+="兒童票\n";
        }else {
            str+="學生票\n";
        }

        //顯示結果
        TextView output=(TextView) findViewById(R.id.lbl_Output);
        output.setText(str);
    }
}