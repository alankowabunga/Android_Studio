package com.example.ch2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    onCreate()方法:初始化靜態活動，即建立活動的使用介面元件
//    protected修飾子存取權限界在public & private之間
//    參數是 Bundle物件(儲存 key-value成對資料的物件) saveInstanceState
//    第一行程式碼使用 duper關鍵字呼叫父類別的 onCreate()方法來初始化活動
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setContentView()方法:載入和顯示布局檔
//        R(R.java檔案的ID)，指向專案[app\res\layout]目錄的布局檔activity_main.xml
    }

    public void button_Add(View view) { // button_Add 為我們設定的 onClick屬性
//        取得 TextView元件後，呼叫get.Text()方法來取得輸入的內容
        int count;
        TextView output=(TextView) findViewById(R.id.lblOutput); //找出使用介面的 TextView元件
        count=Integer.parseInt(output.getText().toString()); // getText()方法取得 editText內容，再轉為整數放入變數 count 中
        count++;
        output.setText(Integer.toString(count));  // 使用 setText() 方法指定顯示內容
//    文字功能主要有兩種，EditText元件(輸入文字內容)、TextView元件(顯示文字內容)
//    通常會先有一個欄位說明用途的 TextView元件(單純說明，非輸出用途)，才有讓使用者輸入的 EditText元件。
    }
    public void button_Reset(View view) { // button_Reset 為我們設定的 onClick屬性
        TextView output=(TextView) findViewById(R.id.lblOutput);
        output.setText("0");
    }
}