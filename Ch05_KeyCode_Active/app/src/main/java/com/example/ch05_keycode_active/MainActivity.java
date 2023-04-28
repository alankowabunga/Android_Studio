package com.example.ch05_keycode_active;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
<<<<<<< HEAD

public class MainActivity extends AppCompatActivity {
=======
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements KeyEvent.Callback{

    TextView output;
>>>>>>> b6bb174ecf3c5409c25b184c8bbd41448b22b689

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
    }
}
=======

        output = (TextView) findViewById(R.id.output);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 按下 BACK 鍵後執行的程式
            output.setText("按下 BACK 鍵...");
            return true;
            /* 如全權處理則 return true，若希望下一個接收者物件也可以處理就 return false*/
        }
        return super.onKeyDown(keyCode, event);
        //當一個類別繼承自另一個類別時，可以使用 super 關鍵字幫助子類別繼承父類別的方法和屬性，並且能夠對它們進行更改或擴充。
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        output.setText("您按下的 KeyDode 編碼: " + keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        return false;
    }

}

>>>>>>> b6bb174ecf3c5409c25b184c8bbd41448b22b689
