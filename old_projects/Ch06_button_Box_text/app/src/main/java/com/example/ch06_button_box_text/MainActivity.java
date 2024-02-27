package com.example.ch06_button_box_text;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*
這個專案練習的 bug 和缺點:
output 輸出的方式，每個監聽事件各自為政 -> 最後輸出結果不可控。
因此要改善的話，應該要讓輸出方式統一，例如建立一個輸出結果的 showResult 函式方法。
*/
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,TextWatcher, CompoundButton.OnCheckedChangeListener {

    TextView output;
    EditText input;
    RadioGroup rg;

    RadioButton male_btn;
    RadioButton female_btn;
    String result;
    String txt;
    int[] chkBoxs = {R.id.Box1,R.id.Box2,R.id.Box3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.output);
        input = (EditText) findViewById(R.id.input);
        rg = (RadioGroup) findViewById(R.id.rg_btns);
        male_btn = (RadioButton) findViewById(R.id.rd_male);
        female_btn = (RadioButton) findViewById(R.id.rd_female);
        /*
            用創建的 checkbox 陣列，儲存的資料為 id 整數資料型態。
            利用 for-each 將陣列中的 id 找到元件放入 box 變數，
            並用 box 變數添增添增事件。
        */
        for (int id : chkBoxs) {
            CheckBox box = (CheckBox) findViewById(id);
            box.setOnCheckedChangeListener(this);
        }

        if (male_btn.isChecked()) {
            Toast.makeText(this, "男生", Toast.LENGTH_SHORT).show();
        } else if (female_btn.isChecked()) {
            Toast.makeText(this, "女生", Toast.LENGTH_SHORT).show();
        }

        // 及時監聽
        rg.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);
        input.addTextChangedListener((TextWatcher) this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        result = "";
        if (rg.getCheckedRadioButtonId() == R.id.rg_1) {
            result += "身高180\n";
            output.setText(result);

        } else if (rg.getCheckedRadioButtonId() == R.id.rg_2) {
            result += "年薪百萬\n";
            output.setText(result);
        } else {
            result += "360度無死角\n";
            output.setText(result);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        txt = "";
        txt += input.getText().toString()+"\n";
        output.setText(txt + result);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //即時顯示當下選取的選項
        switch (buttonView.getId()) {
            case R.id.Box1:
                output.setText("天下武功，為快不破\n");
                break;
            case R.id.Box2:
                output.setText("看看這個男人太狠了\n");
                break;
            case R.id.Box3:
                output.setText("百發百中\n");
                break;
        }
        showResult();
    }

    private void showResult() {
        String str = output.getText().toString()+"\n";
        for (int id : chkBoxs) {
            CheckBox box = (CheckBox) findViewById(id);
            if (box.isChecked()) {
                str+=box.getText()+"\n";
            }
        }
        output.setText((str));
    }
}
