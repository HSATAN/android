package com.example.edison.newworld.activity;

import android.app.Activity;
import android.os.BaseBundle;
import android.os.Bundle;

import com.example.edison.newworld.R;

/**
 * Created by edison on 2017/9/8.
 */

public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.item_list);
    }
}
