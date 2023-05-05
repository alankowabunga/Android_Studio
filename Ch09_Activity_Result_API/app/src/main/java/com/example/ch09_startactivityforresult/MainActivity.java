package com.example.ch09_startactivityforresult;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input1;
    EditText input2;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
        output = (TextView) findViewById(R.id.output);
    }
// 第一階段: Create Contract，自創一個類別去繼承 ActivityResultContract。
    class ResultContract extends ActivityResultContract<Boolean,String>{

        @NonNull
        @Override
        //創建一個啟動目標活動的 Intent 對象
        public Intent createIntent(@NonNull Context context, Boolean aBoolean) {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            //第一個參數代表在主活動創建的 intent 物件本身，第二個參數代表要傳去的目標活動類別。
            Bundle bundle = new Bundle();
            // 將資料放入 bundle 物件中
            bundle.putString("input1", input1.getText().toString());
            bundle.putString("input2", input2.getText().toString());
            intent.putExtras(bundle); //加上 bundle 物件

            return intent; //將創建好的 Intent 對象返回給調用者，即返回給調用這個方法的那個地方
        }

        @Override
        // parseResult 方法: 剖析活動的回傳值，此例是四則運算的結果
        public String parseResult(int i, @Nullable Intent intent) {

            // intent 已經建立好了，getExtras() 對應到目標活動的 intent.putExtras(bundle)>
            Bundle bundle=intent.getExtras();
            Double result = bundle.getDouble("RESULT");
            return result.toString();
        }
    }
//第二階段: Register Contract，建立 ActivityResultLauncher物件 launcher，來註冊 ResultContract 的回撥方法 onActivityResult()。
    ActivityResultLauncher launcher = registerForActivityResult(new ResultContract(), new ActivityResultCallback<String>() {
    @Override
    // onActivityResult 就是回撥方法，參數 result 就是活動的回傳值，取得後顯示結果。2
    public void onActivityResult(String result) {
        output.setText(("計算結果:"+result));
    }
});

    // 按鈕會執行目標的活動: 呼叫 ActivityResultLauncher 物件的 launch()方法。
    public void button_click(View view) {
        launcher.launch(true);
    }
}