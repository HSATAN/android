package com.example.edison.newworld.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.edison.newworld.R;

import java.io.File;

import static android.os.Environment.getDataDirectory;

/**
 * Created by edison on 2017/9/8.
 */

public class FileActivity extends Activity {
    private Button button;
    private TextView textView;
    private EditText editText;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.file_layout);
        button=(Button)findViewById(R.id.button_file);
        textView=(TextView)findViewById(R.id.file_textview);
        editText=(EditText)findViewById(R.id.file_edit);
        editText.setText(Environment.getExternalStorageDirectory().toString()+"/相机/");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path =editText.getText().toString();
                File[] files =new File(path).listFiles();
                String result="";
                for (File file:files){
                    result+=file.getPath()+"\n";
                }
                textView.setText(result);
            }
        });

    }
}
