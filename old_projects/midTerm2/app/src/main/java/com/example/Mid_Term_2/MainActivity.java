package com.example.Mid_Term_2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtern2.R;

public class MainActivity extends AppCompatActivity {
    // 先宣告 View 的變數
    TextView title;
    Button submitButton;
    Button restartButton;
    EditText inputNumber;
    Editable editable;
    TextView historyInput;
    TextView historyResult;
    TextView answer_display;
    // Toast 是畫面下面會跳出來的小提示框
    FrameLayout cover;
    int counter;
    Spinner spinner;
    String result;
    boolean flag_easy = false;
    boolean correct_input = true;
    final Game game = new Game();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 找到畫面中的 View
        title = (TextView) findViewById(R.id.title);
        submitButton = (Button) findViewById(R.id.submitButton);
        restartButton = (Button) findViewById(R.id.restartButton);
        // 輸入的元件、字符序列 editable 、 長度
        inputNumber = (EditText) findViewById(R.id.inputNumber);
        editable = inputNumber.getText();

        answer_display = (TextView) findViewById(R.id.answer_display);

        historyInput = (TextView) findViewById(R.id.history_input);
        historyResult = (TextView) findViewById(R.id.history_result);
        cover = (FrameLayout) findViewById(R.id.cover);
        counter = 0;
        // 下拉式選單
        spinner = (Spinner) findViewById(R.id.mySpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 取值 & 顯示選擇的項目
                result = parent.getItemAtPosition(position).toString();
                Toast.makeText(view.getContext(), "模式:" + result, Toast.LENGTH_SHORT).show();

                switch (result) {
                    case "簡單模式":
                        flag_easy = true;
                        break;
                    case "困難模式":
                        flag_easy = false;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 呼叫生成答案的方法
        generateAnswer();

        final Context that = this;

        // 設定送出按鈕的點擊事件
        submitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                // 當簡單模式輸入重複數字
                if (flag_easy && (game.getC(editable.toString()) > 1)) {
                    correct_input = false;
                    Toast.makeText(getApplicationContext(), "數字不可重複", Toast.LENGTH_SHORT).show();
                }

                // 一定要輸入四個數字才有反應
                if (inputNumber.getText().length() == 4 && correct_input) { // getText() 方法返回的資料類型是 Editable，這是一個可變的字符序列對象

                    answer_display.setText(game.getAnswer()); // 顯示答案
                    answer_display.setVisibility(View.VISIBLE);

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
                } else {
                    Toast.makeText(getApplicationContext(), "請輸入4位數字", Toast.LENGTH_SHORT).show();
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

                answer_display.setText("");
                answer_display.setVisibility(View.INVISIBLE);
                inputNumber.setEnabled(false);
                inputNumber.setEnabled(true);
                submitButton.setEnabled(true);
                generateAnswer();
                historyInput.setText("");
                historyResult.setText("");
                cover.setVisibility(View.INVISIBLE);
                correct_input = true;
            }

        });
    }


    private void generateAnswer() {
        if (flag_easy) {
            game.nonRepeatAnswer();
        } else {
            game.repeatableAnswer();
        }
    }

    // 設置要用哪個 menu 檔當選單
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //取得點選項目的 id
        int id = item.getItemId();

        // 依照 id 判斷，哪個項目被點擊並做出相對應的動作
        if (id == R.id.menu_name) {
            Toast.makeText(this, "我的名子", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.student_id) {

            Toast.makeText(this, "我的學號", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class ResultContract extends ActivityResultContract<Boolean, String> {

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Boolean aBoolean) {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            return intent;
        }

        @Override
        public String parseResult(int i, @Nullable Intent intent) {

            Bundle bundle = intent.getExtras();
            String nickname = bundle.getString("RESULT");
            return nickname;
        }
    }

    //        Register Contract
    ActivityResultLauncher launcher = registerForActivityResult(new ResultContract(), new ActivityResultCallback<String>() {
        @Override
        public void onActivityResult(String name) {
            title.setText(name);
        }
    });

    public void click_setName(View view) {
        launcher.launch(true);
    }
}
