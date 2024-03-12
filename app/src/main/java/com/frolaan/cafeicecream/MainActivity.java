package com.frolaan.cafeicecream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button createIceCreamButton;
    private EditText editTextUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setUpClickListener();
    }

    private void setUpClickListener() {
        createIceCreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clientName = editTextUserName.getText().toString().trim();
                if (clientName.isEmpty()) {
                    Toast.makeText(
                            MainActivity.this,
                            R.string.toast_please_enter_name,
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    Intent intent = CreateIceCreamActivity.newIntent(MainActivity.this, clientName);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

     private void initViews() {
         createIceCreamButton = findViewById(R.id.createIceCreamButton);
         editTextUserName = findViewById(R.id.editTextUserName);
     }

     public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
     }
}