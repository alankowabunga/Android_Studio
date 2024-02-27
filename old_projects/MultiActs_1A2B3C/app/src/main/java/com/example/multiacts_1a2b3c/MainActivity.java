package com.example.multiacts_1a2b3c;


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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // 先宣告 View 的變數
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
    boolean correct_input = false;
    final Game game = new Game();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 找到畫面中的 View
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

        // Spinner 下拉式選單
        spinner = (Spinner) findViewById(R.id.mySpinner);
        String[] data = {"簡單模式", "困難模式"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 取值 & 顯示選擇的項目
                result = parent.getItemAtPosition(position).toString();
                Toast.makeText(view.getContext(), "模式:" + result, Toast.LENGTH_SHORT).show();

                switch (result) {
                    case "簡單模式":
                        clearConfig();
                        flag_easy = true;
                        break;
                    case "困難模式":
                        clearConfig();
                        flag_easy = false;
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);

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

        // 是否有輸入重複的數字
        if (game.getC(editable.toString()) == 0) {
            correct_input = true;
        } else {
            correct_input = false;
        }

        // 設定送出按鈕的點擊事件
        submitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {


                // 一定要輸入四個數字才有反應
                if (inputNumber.getText().length() == 4 && correct_input) { // getText() 方法返回的資料類型是 Editable，這是一個可變的字符序列對象

                    answer_display.setText(game.getAnswer()); // 顯示答案
                    answer_display.setVisibility(View.VISIBLE);

                    // 將使用者輸入的數字跟幾 A 幾 B 放入文字框框中
                    historyInput.setText((inputNumber.getText() + "\n") + historyInput.getText());
                    historyResult.setText((game.checkNonRepeatedAnswer(inputNumber.getText().toString()) + "\n") + historyResult.getText());
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
                    Toast.makeText(getApplicationContext(), "請輸入4位不重複數字", Toast.LENGTH_SHORT).show();
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
                historyInput.setText("");
                historyResult.setText("");
                cover.setVisibility(View.INVISIBLE);
                correct_input = false;
                flag_easy = false;
                generateAnswer();
            }

        });
    }

    private void clearConfig() {
        answer_display.setText("");
        answer_display.setVisibility(View.INVISIBLE);
        inputNumber.setEnabled(false);
        inputNumber.setEnabled(true);
        submitButton.setEnabled(true);
        historyInput.setText("");
        historyResult.setText("");
        cover.setVisibility(View.INVISIBLE);
        flag_easy = false;
    }

    // 生成簡單模式的答案
    private void generateAnswer() {
        if (flag_easy) {
            game.nonRepeatAnswer();
        }
    }

    // 設置要用哪個 menu 檔當選單
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    // menu 操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //取得 menu 點選項目的 id
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
}

