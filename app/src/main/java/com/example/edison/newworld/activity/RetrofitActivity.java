package com.example.edison.newworld.activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edison.newworld.DataFactory.Blog;
import com.example.edison.newworld.DataFactory.Result;
import com.example.edison.newworld.R;
import com.example.edison.newworld.api_retrofit.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
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
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;

import static android.R.attr.path;

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
    private Button button_blog;
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
        button_blog=(Button)findViewById(R.id.button_blog);
        button_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBlog();
            }
        });
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
        File[] files =new File(Environment.getExternalStorageDirectory().toString()+"/相机/").listFiles();
        File image=null;
        for (File image_temp:files){
           if (image_temp.getPath().indexOf("jpg")>0)
           {
               image=image_temp;
               break;
           }
        }
        RequestBody imagefile=RequestBody.create(MediaType.parse("image/jpg"),image);
        MultipartBody.Part iamgepart=MultipartBody.Part.createFormData("image",image.getName(),imagefile);
//        RequestBody.create(mediaType)
        MultipartBody.Part filepart=MultipartBody.Part.createFormData("file","test.txt",file);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.106:9999/machinelearning/")
                .build();
        Api api=retrofit.create(Api.class);
        Call<ResponseBody> call=api.uploadFile(name,filepart,iamgepart);
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
    public void createBlog()
    {
        Gson gson=new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.106:9999/machinelearning/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
       Api api=retrofit.create(Api.class);
        Blog blog=new Blog();
        blog.content="这是新建的blog";
        blog.title="测试";
        blog.author="黄开杰";
        Call<Result<Blog>> call=api.createBlog(blog);
        call.enqueue(new Callback<Result<Blog>>() {
            @Override
            public void onResponse(Call<Result<Blog>> call, Response<Result<Blog>> response) {
                System.out.println(response.body());
                Result<Blog> blogResult=response.body();
                textView.setText(blogResult.toString());
                System.out.println(blogResult);
            }

            @Override
            public void onFailure(Call<Result<Blog>> call, Throwable t) {

            }
        });
    }
}
