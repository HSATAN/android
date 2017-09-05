package com.example.edison.newworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by huangkaijie on 2017/9/5.
 */

public class NewActivity extends Activity {

    private MyMainActivity activity;
    private Button button;
    private Button button_item;
    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        textView=(TextView)findViewById(R.id.textView);
        Bundle bundle=getIntent().getExtras();
        button_item=(Button)findViewById(R.id.button_item);
        textView.setText(bundle.getString("text"));
        button=(Button)findViewById(R.id.button_main_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewActivity.this,MyMainActivity.class);
                startActivity(intent);
            }

        });
        button_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewActivity.this,ItemActivity.class);
                startActivity(intent);
            }
        });
    }
}
