package com.demo.carteblanchefirebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button mSecondButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mSecondButton = (Button) findViewById(R.id.mySecondButton);

        mSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =
                        new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
