package com.example.ch05_3_textsize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {


    TextView output;
    Button btn1;
    Button btn2;
    Button btn3;
    float original_size = 16;
    float size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.text_output);
        output.setTextSize(original_size);
        size = output.getTextSize();

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);

        btn1.setOnClickListener(this);
        btn1.setOnLongClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
//    介面元件 共用 事件處理 -> 用 View 物件 來判斷是哪一個元件觸發事件
// 帶小數點的數值字面量，默認情況下被當作 double 類型的值。在數字後面加上字母 'f' 或 'F'，表示強制將該數字轉換為 float 類型。
    public void onClick(View view) {
        if (view.getId() == R.id.button1) {

            output.setTextSize(original_size + 10);
            btn1.setText("長按復原");

        }
        if (view.getId() == R.id.button2) {
            size++;
            btn1.setText("長按復原");

        } else if(view.getId()==R.id.button3){

            size--;
            btn1.setText("長按復原");
        }
        output.setTextSize(size);
    }

    @Override
    public boolean onLongClick(View v) {
        output.setTextSize(original_size);
        btn1.setText("點擊");
        return true;
    }
}


