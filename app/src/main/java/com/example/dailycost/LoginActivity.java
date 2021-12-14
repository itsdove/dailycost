package com.example.dailycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        TextView username=findViewById(R.id.username);
        TextView password=findViewById(R.id.password);
        Button button=findViewById(R.id.login);
        button.setOnClickListener(v -> {
            if(username.getText().toString().length()!=0&&password.getText().toString().length()!=0)
            {Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);}
            else
                Toast.makeText(getApplicationContext(),"请输入账号和密码",Toast.LENGTH_SHORT).show();
        });

    }
}