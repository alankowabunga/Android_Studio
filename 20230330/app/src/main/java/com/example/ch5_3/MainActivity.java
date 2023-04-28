package com.example.ch5_3;

import static android.widget.RadioGroup.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{
    private TextView output;
    private TextView output_Ans;
    private TextView output_State;
    private EditText input;
    private RadioGroup diff_change;
    private RadioButton NoOverlap;
    private RadioButton YesOverlap;
    String[] four_ran_num = new String[4];
    int first_flag = 1;
    int game_mode = 0;  //0 = NoOverlap Number
    boolean overlap_flag = false;
    boolean overlap_Num_1_flag = false , overlap_Num_2_flag = false;
    int overlap_Num_1 = 0 , overlap_Num_2 = 0;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.lblOutput);
        output_Ans = findViewById(R.id.lblOutput_Ans);
        output_State = findViewById(R.id.lblOutput_State);
        input = findViewById(R.id.editTextNumber_input);
        diff_change = findViewById(R.id.diff_change);
        NoOverlap = (RadioButton) findViewById(R.id.rdbNoOverlap);
        YesOverlap = (RadioButton) findViewById(R.id.rdbYesOverlap);

        diff_change.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int difficulty) {
                difficulty_change(difficulty);
            }
        });

        //如果是第一次使用則產生隨機亂數
        if (first_flag == 1)
        {
            if(game_mode == 0)
            {
                Random_Number_generate();
            }
            else
            {
                Overlap_Random_Number_generate();
            }
            first_flag = 0;
        }

        output.setText("0A0B");

    }

    @SuppressLint("SetTextI18n")
    public void OnClick_confirm(View view) {
        String[] Gus = new String[4];
        int input_temp, A = 0, B = 0, C = 0;

        //判斷是否有輸入(可避免無輸入當機)
        if("".equals(input.getText().toString().trim()))
        {
            Toast.makeText(this, "請輸入四位數字", Toast.LENGTH_SHORT).show();
            //output_State.setText("請輸入四位數字");
        }
        else
        {
            input_temp = Integer.parseInt(input.getText().toString());

            //確認是否為四位數
            if (input.getText().toString().length() == 4)
            {
                //將四位數分開並存成字串
                Gus[0] = Integer.toString(input_temp / 1000);
                Gus[1] = Integer.toString(input_temp / 100 % 10);
                Gus[2] = Integer.toString(input_temp / 10 % 10);
                Gus[3] = Integer.toString(input_temp % 10);

                for (int first = 0; first < 4; first++) {
                    for (int second = 0; second < 4; second++) {
                        //數字正確且位置正確
                        if (Gus[first].equals(four_ran_num[second]) && first == second)
                        {
                            A++;
                        }
                        //數字正確但位置不正確
                        else if (Gus[first].equals(four_ran_num[second]) && first != second)
                        {
                            B++;
                        }

                        if (overlap_flag)
                        {
                            if(overlap_Num_1 == Integer.parseInt(Gus[first]) && overlap_Num_1_flag && C != 2)
                            {
                                C = 1;
                            }

                            if(overlap_Num_2 == Integer.parseInt(Gus[first]) && overlap_Num_2_flag)
                            {
                                C = 2;
                            }
                        }
                    }
                }

                if(game_mode == 0)
                    output.setText(A + "A" + B + "B");
                else
                    output.setText(A + "A" + B + "B" + C + "C");

                output_State.setText("");
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("違規")  // 指定對話方塊標題文字
                        .setMessage("請輸入四位數字")  // 指定對話方塊訊息文字
                        .setCancelable(true)    // 指定對話方塊訊息文字
                        .setPositiveButton("確定", null)  // 設定按鈕和事件處理程序
                        .show();  // 顯示對話方塊
                //Toast.makeText(this, "請輸入四位數字", Toast.LENGTH_SHORT).show();
                //output_State.setText("請輸入四位數字");
            }
        }
    }

    //重新開始
    @SuppressLint("SetTextI18n")
    public void onClick_Restart(View view) {
        initialization();
    }

    //顯示答案
    @SuppressLint("SetTextI18n")
    public void onClick_ShowAns(View view) {
        output_Ans.setText("答案 = "+four_ran_num[0]+four_ran_num[1]+four_ran_num[2]+four_ran_num[3]);
        output_Ans.setVisibility(View.VISIBLE);
    }

    //產生隨機且不重複之四位數
    public void Random_Number_generate(){
        int temp_in_1 = 0, temp_in_2 = 0, temp_in_3 = 0, temp_in_4 = 0;
        int flag_1 = 0, flag_2 = 0, flag_3 = 0;

        temp_in_1 = (int) (Math.random() * 10);
        four_ran_num[0] = Integer.toString(temp_in_1);

        //重複產生隨機數字直到不與第一個數字重複
        while (flag_1 == 0)
        {
            temp_in_2 = (int) (Math.random() * 10);
            if (temp_in_2 != temp_in_1)
            {
                four_ran_num[1] = Integer.toString(temp_in_2);
                flag_1 = 1;
            }
        }

        while (flag_2 == 0)
        {
            temp_in_3 = (int) (Math.random() * 10);
            if (temp_in_3 != temp_in_1 && temp_in_3 != temp_in_2)
            {
                four_ran_num[2] = Integer.toString(temp_in_3);
                flag_2 = 1;
            }
        }

        while (flag_3 == 0)
        {
            temp_in_4 = (int) (Math.random() * 10);
            if (temp_in_4 != temp_in_1 && temp_in_4 != temp_in_2 && temp_in_4 != temp_in_3)
            {
                four_ran_num[3] = Integer.toString(temp_in_4);
                flag_3 = 1;
            }
        }
    }

    //產生隨機可能重複之四位數，判斷是否有重複，將重複數字記起來
    public void Overlap_Random_Number_generate(){

        //產生隨機可能重複之四位數
        for(int four = 0 ; four < 4 ; four++)
        {
            four_ran_num[four] = Integer.toString((int) (Math.random() * 10));
        }

        //答案重複數字判斷
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                //答案是否有重複數字
                if(four_ran_num[i].equals(four_ran_num[j]) && i != j)
                {
                    //至少有一個數字重複
                    overlap_flag = true;

                    //第一個儲存重複數字欄位未使用
                    if (!overlap_Num_1_flag)
                    {
                        overlap_Num_1 = Integer.parseInt(four_ran_num[i]);
                        overlap_Num_1_flag = true;
                    }

                    //第二個儲存重複數字欄位未使用,且不等於第一個欄位數字
                    if(!overlap_Num_2_flag && overlap_Num_1_flag &&
                            Integer.parseInt(four_ran_num[i]) != overlap_Num_1)
                    {
                        overlap_Num_2 = Integer.parseInt(four_ran_num[i]);
                        overlap_Num_2_flag = true;
                    }
                }
            }
        }
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    public void difficulty_change(int difficulty){
        switch (difficulty){
            case R.id.rdbNoOverlap:
            {
                game_mode = 0;
                break;
            }
            case R.id.rdbYesOverlap:
            {
                game_mode = 1;
                break;
            }
        }
        initialization();
    }

    @SuppressLint("SetTextI18n")
    public void initialization(){
        //關閉答案顯示
        output_Ans.setVisibility(View.INVISIBLE);

        //初始化答案重複旗標
        overlap_flag = false;
        overlap_Num_1_flag = false;
        overlap_Num_2_flag = false;

        //初始化結果欄位
        if(game_mode == 0)
            output.setText("0A0B");
        else
            output.setText("0A0B0C");

        //初始化輸入欄位
        input.setText("");

        //初始化狀態欄位
        output_State.setText("");

        //初始化答案欄位
        Arrays.fill(four_ran_num, "0");

        //重新產生答案欄位
        if(game_mode == 0)
            Random_Number_generate();
        else
            Overlap_Random_Number_generate();
    }
}