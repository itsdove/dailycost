package com.example.dailycost.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dailycost.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addlayout);
        EditText money=findViewById(R.id.number);
        EditText reason=findViewById(R.id.reason);
        Button button=findViewById(R.id.b1);
        Intent intent1=getIntent();
        String r1=intent1.getStringExtra("reason");
        String m1=String.valueOf(intent1.getDoubleExtra("money",0.0));
        money.setText(m1);
        reason.setText(r1);
        final int[] i = {0};
        i[0]=intent1.getIntExtra("ima",-1);
        LinearLayout layout1=findViewById(R.id.l1);
        layout1.setOnClickListener(v -> {
           reason.setText("其他");
            i[0] =R.drawable.other;
        });
        LinearLayout layout2=findViewById(R.id.l2);
        layout2.setOnClickListener(v -> {
            reason.setText("交通出行");
            i[0] =R.drawable.bus;
        });
        LinearLayout layout3=findViewById(R.id.l3);
        layout3.setOnClickListener(v -> {
            reason.setText("吃喝饮食");
            i[0] =R.drawable.eat;
        });
        LinearLayout layout4=findViewById(R.id.l4);
        layout4.setOnClickListener(v -> {
            reason.setText("通讯费用");
            i[0] =R.drawable.huafei;
        });
        LinearLayout layout5=findViewById(R.id.l5);
        layout5.setOnClickListener(v -> {
            reason.setText("房租水电");
            i[0] =R.drawable.rent;
        });
        LinearLayout layout6=findViewById(R.id.l6);
        layout6.setOnClickListener(v -> {
            reason.setText("消费购物");
            i[0] =R.drawable.shop;
        });
        LinearLayout layout7=findViewById(R.id.l7);
        layout7.setOnClickListener(v -> {
            reason.setText("运动健身");
            i[0] =R.drawable.sport;
        });
        findViewById(R.id.l8).setOnClickListener(v -> {
            reason.setText("旅游出行");
            i[0] =R.drawable.travel;
        });
        findViewById(R.id.l9).setOnClickListener(v -> {
            reason.setText("基金理财");
            i[0] =R.drawable.jj;
        });
        findViewById(R.id.l10).setOnClickListener(v -> {
            reason.setText("医疗用品");
            i[0] =R.drawable.travel;
        });
        findViewById(R.id.l11).setOnClickListener(v -> {
            reason.setText("日常用品");
            i[0] =R.drawable.daily;
        });

        button.setOnClickListener(v -> {
            int why=intent1.getIntExtra("why",0);
            Intent intent=new Intent();
            intent.putExtra("money",Double.parseDouble(money.getText().toString()));
            intent.putExtra("reason",reason.getText().toString());
            intent.putExtra("imagine",i[0]);
            if(why==1)
            {intent.putExtra("pos",intent1.getIntExtra("pos",0));
                setResult(RESULT_CANCELED,intent);}
            else
            setResult(RESULT_OK,intent);
            finish();
        });

    }

}