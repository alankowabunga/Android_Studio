package com.example.Mid_Term_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.midtern2.R;

public class SecondActivity extends AppCompatActivity {
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameInput = findViewById(R.id.nickname_input);
    }

    String name = String.valueOf(nameInput.getText());

    public void click_return(View view) {

        Intent rIntent = new Intent();
        Bundle rBundle = new Bundle();
        rBundle.putString("RESULT", name);
        rIntent.putExtras(rBundle);
        setResult(RESULT_OK, rIntent);
        finish();
    }
}