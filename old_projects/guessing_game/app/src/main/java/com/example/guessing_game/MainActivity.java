package com.example.guessing_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//    先取得介面元件，變數不能在 onCreate 前賦值
    EditText Guess;
    Button Btn;
    TextView result;
    String computerGuess ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //使用 setContentView() 將 activity_main.xml & MainActivity.java 串聯在一起

//        程式初始化時就先產生答案: 4 位數亂數。(將變數宣告在這裡，仔點擊事件的函式也可使用)
        for (int i = 0; i < 4; i++) {
            computerGuess = "";
            computerGuess += (int) (Math.random() * 10); //Math.random()生成0~1 之間的浮點數，乘十、再強制轉換成整數 int 。
        }
    }

//    送出按鈕-點擊事件
    public void onclick_Btn(View view) {
        Guess=(EditText) findViewById(R.id.input_Txt);
        Btn=(Button) findViewById(R.id.send_Btn);
        result=(TextView) findViewById(R.id.output_Txt);

        String userGuess=Guess.getText().toString(); //使用者輸入的為文字型態，getText()取得後先轉為字串再轉為整數
        String msg; //宣告告知結果的字串型態變數;

        while(true){
            int A=0,B=0;
            boolean check[]=new boolean[4]; //紀錄每個位數是否檢查過
            Arrays.fill(check, false);

            //檢查有幾A
            for(int i=0;i<4;i++){
                if(computerGuess.charAt(i)==userGuess.charAt(i)){ //charAt() 使用在字串中，因此 userGuess必須是 String type
                    A++;
                    check[i]=true;
                }
            }
            //檢查有幾B
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(!check[j] && computerGuess.charAt(j)==userGuess.charAt(i)){
                        B++;
                        check[j]=true;
                        break;
                    }
                }
            }

            //Output
            if(A==4){
                result.setText("Correct");
                break;
            }
            else{
                result.setText(A+"A"+B+"B");
            }
        }
    } //onclick end
} // MainActivity end