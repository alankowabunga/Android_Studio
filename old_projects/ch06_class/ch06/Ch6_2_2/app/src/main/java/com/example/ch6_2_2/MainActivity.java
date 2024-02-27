package com.example.ch6_2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {
    private TextView output;
    private int[] chkIDs = {R.id.chkOriginal, R.id.chkBeef, R.id.chkSeafood};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.lblOutput);
        for (int id : chkIDs) {
            CheckBox chk = (CheckBox) findViewById(id);
            chk.setOnCheckedChangeListener(this);
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.chkOriginal:
                output.setText("你點選的是原味...\n");
                break;
            case R.id.chkBeef:
                output.setText("你點選的是牛肉...\n");
                break;
            case R.id.chkSeafood:
                output.setText("你點選的是海鮮...\n");
                break;
        }
        showOrder();
    }
    public void showOrder() {
        String str = output.getText().toString();
        // 檢查勾選哪些披薩
        for (int id : chkIDs) {
            CheckBox chk = (CheckBox) findViewById(id);
            if (chk.isChecked())
                str += chk.getText() + "\n";
        }
        // 顯示訂購項目
        output.setText(str);
    }
}