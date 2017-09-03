package com.example.edison.newworld;

/**
 * Created by edison on 2017/9/3.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MyMainActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    private RadioGroup rgs;
    public List<Fragment> fragments = new ArrayList<Fragment>();

    public String hello = "hello ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments.add(new Hot());
        fragments.add(new Single());
        fragments.add(new Other());
        fragments.add(new Account());


        rgs = (RadioGroup) findViewById(R.id.tabs_rg);

        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content, rgs);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener(){
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                System.out.println("Extra---- " + index + " checked!!! ");
            }
        });

    }

}

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class MainActivity extends AppCompatActivity{
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        final TextView text1=(TextView) findViewById(R.id.text1);
//        Button change_text1_button=(Button)findViewById(R.id.change_text1_button);
//        change_text1_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                text1.setText("黄凯杰");
//            }
//        });

//change_text1_button.setOnClickListener(this);

//
//    }
////    @Override
////    public void onClick(View v){
////        switch (v.getId()){
////            case R.id.change_text1_button:
////                ((TextView)findViewById(R.id.text1)).setText("黄开杰");
////        }
////    }
//}
