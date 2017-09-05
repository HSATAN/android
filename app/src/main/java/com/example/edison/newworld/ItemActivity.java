package com.example.edison.newworld;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huangkaijie on 2017/9/5.
 */

public class ItemActivity extends Activity {
    @BindView(R.id.rv_text_list)
    RecyclerView rvTextList;


    @Override
    public void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.item_main_list);
        ButterKnife.bind(this);
        rvTextList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvTextList.setAdapter(new TextListAdapter(this));
    }
}
