package com.example.ch09_intent_bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        output=(TextView) findViewById(R.id.textView2) ;

        calculation();
    }

    private void calculation() {

        int num,result;

        //取得傳遞的資料，建立 bundle 物件接著用 getIntent().getExtras()方法 -> 對應到傳遞資料時的 intent.putExtras() 方法。
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) { // 判斷是否攜帶資料
            num = Integer.parseInt(bundle.getString("tenFolds"));
            result = num*10;

            output.setText("乘十的值為: "+Integer.toString(result));
        }
    }

    public void button2_click(View view) {
        finish();
    }
}