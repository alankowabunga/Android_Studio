package com.example.ch09_intent_bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得輸入資料
        input =(EditText) findViewById(R.id.input);
    }

//建立 intent object來啟用其他活動，建構子參數第一個 this 是活動自己，第二個參數是目標類別名稱
    public void button1_click(View view) {

        // 建立 Intent 物件
        Intent intent = new Intent(this,SecondActivity.class);

        //建立 bundle 物件來將資料做包裝，putString()方法將字串資料放進去 bundle，接著可以用 intent 的 putExtras() 方法將 bundle 放入一併傳給目標活動。
        Bundle bundle = new Bundle();
        bundle.putString("tenFolds", input.getText().toString()); // 其參數為 key-value ，第一個參數 key 會需要它取出資料內容、第二個參數是 value。
        intent.putExtras(bundle);

        startActivity(intent);
    }
}