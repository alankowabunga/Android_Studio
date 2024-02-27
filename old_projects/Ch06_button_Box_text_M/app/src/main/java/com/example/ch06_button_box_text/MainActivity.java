package com.example.ch06_button_box_text;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/*
這個專案練習的 bug 和缺點:
output 輸出的方式，每個監聽事件各自為政 -> 最後輸出結果不可控。
因此要改善的話，應該要讓輸出方式統一，例如建立一個輸出結果的 showResult 函式方法。
*/
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, TextWatcher, CompoundButton.OnCheckedChangeListener {

    TextView output;
    EditText input;
    RadioGroup rg;

    String result; //統一的結果字串
    int[] chkBoxs = {R.id.Box1, R.id.Box2, R.id.Box3};
    //id 為整數資料型態

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.output);
        input = (EditText) findViewById(R.id.input);
        rg = (RadioGroup) findViewById(R.id.rg_btns);

        /*
            利用 for-each 找 checkbox 陣列中的 id 取得元件，並放入 box 變數設定 監聽事件。
        */
        for (int id : chkBoxs) {
            CheckBox box = (CheckBox) findViewById(id);
            box.setOnCheckedChangeListener(this);
        }


        // 及時監聽
        rg.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);
        input.addTextChangedListener((TextWatcher) this);
    }

    // RadioGroup
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        /*
         要記得將選項的 id 當做引數一並傳給 showResult函式處理。
         1. 如果沒有傳引數， showResult函式根本沒有按鈕的資訊來源能做處理。
         2. 參數設定是 id 整數資料型態，給的引數必須符合。
         */
        showResult(checkedId);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    // 輸入文字
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        /*
        因為只要輸入有變動就會執行這裡的程式，因此不一定要在這裡處理(取得輸入框的文字並顯示在 output上)
        我們ˇ也可以去呼叫另一個函式做同樣的動作，並和其他元件的即時監聽一起處理，讓程式碼更可閱讀、結果更準確。
         */
        showResult(rg.getCheckedRadioButtonId()); //需要代表 id 的參數，我們傳被選取的 radioGroup button id
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    // Checkbox
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // 當下選取項目時，及時要顯示的結果
        showResult(buttonView.getId());
    }

    //統一顯示結果

    private void showResult(int Id) {

        result = "";

        // RadioGroup Buttons: 有傳 id 進來，不用再用 rg.getCheckedRadioButtonId() 找一次。
        if (rg.getCheckedRadioButtonId() == R.id.rg_1) {
            result += "手段高\n";

        } else if (rg.getCheckedRadioButtonId() == R.id.rg_2) {
            result += "學富五車\n";
        } else if(rg.getCheckedRadioButtonId()==R.id.rg_3){
            result += "360度無死角\n";
        }
        // CheckBox 01:被選取的checkbox 文字 -> box.getText()
        for (int id : chkBoxs) {
            CheckBox box = (CheckBox) findViewById(id);
            if (box.isChecked()) {
                result =result + box.getText() +  "\n";
            }
        }
        // Checkbox 02: 設定每個選項要顯示的文字
        switch (Id) {
            case R.id.Box1:
                result+="天下武功，為快不破\n";
                break;
            case R.id.Box2:
                result+="看看這個男人太狠了\n";
                break;
            case R.id.Box3:
                result+="百發百中\n";
                break;
        }
        // Text : 找到 input 當前的輸入值 -> 因有設定 addTextChangedListener() 所以會即時更新
        String str = input.getText().toString() + "\n";
        result=str+result;
        output.setText(result);
    }
}
