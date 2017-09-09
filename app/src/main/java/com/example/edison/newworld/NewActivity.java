package com.example.edison.newworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.edison.newworld.activity.FileActivity;
import com.example.edison.newworld.activity.RXActivity;
import com.example.edison.newworld.activity.RetrofitActivity;
import com.example.edison.newworld.activity.TestActivity;
import com.example.edison.newworld.network_utils.GetRequest;
import com.example.edison.newworld.network_utils.PostRequest;

import java.io.BufferedReader;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by huangkaijie on 2017/9/5.
 */

public class NewActivity extends Activity {

    private MyMainActivity activity;
    private Button button;
    private Button button_item;
    private TextView textView;
    private Button button_retrofit;
    private Button button_new_activity;
    private Button button_file_activity;
    private Button button_to_activity;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        textView=(TextView)findViewById(R.id.textView);
        Bundle bundle=getIntent().getExtras();
        button_item=(Button)findViewById(R.id.button_item);
        textView.setText(bundle.getString("text"));
        button_retrofit=(Button)findViewById(R.id.button_retrofit);
        button_file_activity=(Button)findViewById(R.id.button_file_activity);
        button_to_activity=(Button)findViewById(R.id.button_to_rxjava);
        button_to_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewActivity.this, RXActivity.class);
                startActivity(intent);
            }
        });
        button_file_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewActivity.this, FileActivity.class);
                startActivity(intent);
            }
        });
        button=(Button)findViewById(R.id.button_main_activity);

        button_new_activity=(Button)findViewById(R.id.button_new_activity_retrofit);
        button_new_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewActivity.this, RetrofitActivity.class);
                startActivity(intent);
            }
        });
        button_retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewActivity.this, GetRequest.class);
                startActivity(intent);
            }
        });

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
