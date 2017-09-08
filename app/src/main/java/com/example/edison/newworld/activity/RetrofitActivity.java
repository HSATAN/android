package com.example.edison.newworld.activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edison.newworld.R;
import com.example.edison.newworld.api_retrofit.Api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

/**
 * Created by edison on 2017/9/7.
 */

public class RetrofitActivity extends Activity {

    private Button button;
    private Button button_post;
    private TextView textView;
    private EditText editText_name;
    private EditText editText_password;
    private Button button_file;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.retrofit_activity);
        ButterKnife.bind(this);
        button=(Button)findViewById(R.id.button_retrofit_activity);
        textView=(TextView)findViewById(R.id.viewtext_retrofit_activity);
        editText_name=(EditText)findViewById(R.id.name_textview);
        editText_password=(EditText)findViewById(R.id.password_view);
        button_post=(Button)findViewById(R.id.post_button);
        button_file=(Button)findViewById(R.id.button_file);
        button_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadfile();
            }
        });
        button_post.setClickable(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
        button_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post();
            }
        });
    }
    public void post(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.0.106:9999/machinelearning/")
                .build();
        Api api=retrofit.create(Api.class);
        String name=editText_name.getText().toString();
        if (name.isEmpty()){
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        String password=editText_password.getText().toString();
//        Call<ResponseBody> call=api.getUserInfo(name,password);
        Map<String,String> map=new HashMap<>();
        map.put("username",name);
        map.put("password",password);
        Call<ResponseBody> call=api.getMap(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    textView.setText(response.body().string());
                }catch (Exception e)
                {
                    System.out.println(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void request(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.106:9999/machinelearning/")
                .build();
        Api api=retrofit.create(Api.class);
        Call<ResponseBody> call=api.getName(1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    textView.setText(response.body().string());
                }catch (Exception e){
                    System.out.println(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            t.printStackTrace();
            }
        });

    }
    public void uploadfile()
    {
        MediaType mediaType=MediaType.parse("text/plain");
        RequestBody name= RequestBody.create(mediaType,"黄开杰");
        RequestBody file=RequestBody.create(MediaType.parse("application/octet-stream"),"只是魔力文件得内瓤");
//        RequestBody.create(mediaType)
        MultipartBody.Part filepart=MultipartBody.Part.createFormData("file","test.txt",file);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.106:9999/machinelearning/")
                .build();
        Api api=retrofit.create(Api.class);
        Call<ResponseBody> call=api.uploadFile(name,filepart);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    textView.setText(response.body().string());
                }catch (Exception e){
                    System.out.println(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }
}
