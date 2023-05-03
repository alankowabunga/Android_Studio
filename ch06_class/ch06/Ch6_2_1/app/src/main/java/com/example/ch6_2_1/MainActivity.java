package com.example.ch6_2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void button_Click(View view) {
        String str = "";
        // 檢查勾選哪些披薩
        CheckBox original = (CheckBox) findViewById(R.id.chkOriginal);
        if (original.isChecked())
            str += original.getText() + "\n";
        CheckBox beef = (CheckBox) findViewById(R.id.chkBeef);
        if (beef.isChecked())
            str += beef.getText() + "\n";
        CheckBox seafood = (CheckBox) findViewById(R.id.chkSeafood);
        if (seafood.isChecked())
            str += seafood.getText() + "\n";
        // 顯示訂購項目
        TextView output = (TextView) findViewById(R.id.lblOutput);
        output.setText(str);
    }
}