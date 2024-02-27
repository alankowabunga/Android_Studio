package com.example.ch09_startactivityforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SecondActivity extends AppCompatActivity {

    RadioButton add_btn;
    RadioButton minus_btn;
    RadioButton multiply_btn;
    RadioButton divide_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        add_btn=(RadioButton) findViewById(R.id.add);
        minus_btn=(RadioButton) findViewById(R.id.minus);
        multiply_btn=(RadioButton) findViewById(R.id.multiply);
        divide_btn=(RadioButton) findViewById(R.id.divide);

    }

    public void button2_click(View view) {

        double result = 0.0; // 給予變數初始值避免報錯(不知道給什麼可以寫 null)

        // 取得 bundle 物件
        Bundle bundle= this.getIntent().getExtras();
        if(bundle == null) return;// 如果沒有傳遞資料，就沒有資料回傳。

        // 取得 input 值
        int num1 = Integer.parseInt(bundle.getString("input1"));
        int num2 = Integer.parseInt(bundle.getString("input2"));

        // 根據 RadioButton 選擇的運算子，算出結果。
        if (add_btn.isChecked()) {
            result = num1 + num2;
        }
        if (minus_btn.isChecked()) {
            result = num1 - num2;
        }
        if (multiply_btn.isChecked()) {
            result = num1*num2;
        }
        if (divide_btn.isChecked()) {
            result=num1/(double) num2;
        }


        //回傳資料，切換活動需要 intent 物件、傳遞資料需要 bundle 物件放入 intent 中。
        Intent rIntent = new Intent();
        Bundle rBundle = new Bundle();
        rBundle.putDouble("RESULT",result); // 將資料放入 bundle 物件
        rIntent.putExtras(rBundle); // 將 bundle 物件放入 intent 物件
        setResult(RESULT_OK,rIntent); // 使用 setResult() 方法設定回傳結果
        finish();

    }
}