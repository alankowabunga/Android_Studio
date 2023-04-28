package com.example.mid_term_2;

import androidx.appcompat.app.AppCompatActivity;
// Android Studio 貼心幫忙引入函式庫
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    // 先宣告 View 的變數
    Button submitButton;
    Button restartButton;
    EditText inputNumber;
    TextView historyInput;
    TextView historyResult;
    // Toast 是畫面下面會跳出來的小提示框
    Toast toast;
    FrameLayout cover;
    ToggleButton toggleButton;
    Boolean flag_hard;
    int counter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到畫面中的 View
        submitButton = (Button) findViewById(R.id.submitButton);
        restartButton = (Button) findViewById(R.id.restartButton);
        inputNumber = (EditText) findViewById(R.id.inputNumber);
        historyInput = (TextView) findViewById(R.id.history_input);
        historyResult = (TextView) findViewById(R.id.history_result);
        cover = (FrameLayout) findViewById(R.id.cover);
        counter = 0;
        flag_hard = false;
        toggleButton = (ToggleButton) findViewById(R.id.toggle_btn);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggleButton.isChecked()) {
                    flag_hard = true;
                    Toast.makeText(getApplicationContext(),"數字可重複",Toast.LENGTH_SHORT);
                } else {
                    flag_hard = false;
                    Toast.makeText(getApplicationContext(),"數字不可重複",Toast.LENGTH_SHORT);
                }
            }
        });

        // 宣告一個 Game 實體
        final Game game = new Game();
        // 產生一個隨機的答案
        game.generateAnswer();


        final Context that = this;
        // 設定送出按鈕的點擊事件
        submitButton.setOnClickListener(new View.OnClickListener() {
            //            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // 一定要輸入四個數字才有反應
                if (inputNumber.getText().length() == 4) {
                    // 將使用者輸入的數字跟幾 A 幾 B 放入文字框框中
                    historyInput.setText((inputNumber.getText() + "\n") + historyInput.getText());
                    historyResult.setText((game.checkAnswer(inputNumber.getText().toString()) + "\n") + historyResult.getText());
                    // 清空輸入框
                    inputNumber.setText("");
                    // 如果猜中了
                    if (game.isWin()) {
                        // 跳出獲勝的訊息
                        Toast.makeText(that, "You win", Toast.LENGTH_LONG).show();
                        inputNumber.setEnabled(false);
                        submitButton.setEnabled(false);
                        cover.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        // 設定重新開始按鈕的點擊事件
        restartButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (game.isWin()) {
                    Toast.makeText(that, ("Game restarted"), Toast.LENGTH_LONG).show();
                } else {
                    // 沒有獲勝的話會顯示答案
                    Toast.makeText(that, ("Last answer: " + game.getAnswer() + "\n\n   Game restarted"), Toast.LENGTH_LONG).show();
                }
                inputNumber.setEnabled(false);
                inputNumber.setEnabled(true);
                submitButton.setEnabled(true);
                game.generateAnswer();
                historyInput.setText("");
                historyResult.setText("");
                cover.setVisibility(View.INVISIBLE);
            }
        });
    }
}