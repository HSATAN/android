package com.example.edison.newworld;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * Author: wangjie  email:tiantian.china.2@gmail.com
 * Date: 13-6-14
 * Time: 下午2:39
 */
public class Hot extends Fragment{

    private MyMainActivity activity;
    private  ImageView imageView;
    private Button button;
    private Button button_new_activity;
    private Handler handler;
    @Override
    public void onAttach(Context activity) {

        super.onAttach(activity);
        System.out.println("AAAAAAAAAA____onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("AAAAAAAAAA____onCreateView");
        return inflater.inflate(R.layout.tab_a, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity=(MyMainActivity)getActivity();
        //这个方法会返回当前Fragment所附加的Activity，当Fragment生命周期结束并销毁时，getActivity()返回的是null
        //所以在使用时要注意判断null或者捕获空指针异常。
        handler=new Handler();
        imageView=(ImageView)activity.fragments.get(0).getView().findViewById(R.id.image);
        button=(Button)activity.fragments.get(0).getView().findViewById(R.id.button);
        button_new_activity=(Button)activity.fragments.get(0).getView().findViewById(R.id.button_new_activity) ;
        System.out.println("AAAAAAAAAA____onCreate");
        System.out.println("AAAAAAAAAA____onActivityCreated");

        button_new_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity,NewActivity.class);//第一个参数是一个activity，可以用getActivity（）得到，或者类名加上.class，第二个是要跳到的新activity
                Bundle bundle=new Bundle();
                bundle.putString("text",button.getText().toString());//向bundle中添加键值对
                intent.putExtras(bundle);
                startActivity(intent);//开启新的activity
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final Bitmap bitmap=getImageBitmap("http://www.myenger.com/static/pic/doudou1.jpg");
                                Hot.this.handler.post(new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Hot.this.imageView.setImageBitmap(bitmap);
                                    }
                                }));
                            }catch (Exception e){
                                System.out.println(e);
                            }


                        }
                    }).start();




            }
        });

    }
    public Bitmap getImageBitmap(String url) {
        URL imgUrl = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imgUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    public void onStart() {
        super.onStart();
        System.out.println("AAAAAAAAAA____onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("AAAAAAAAAA____onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("AAAAAAAAAA____onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("AAAAAAAAAA____onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("AAAAAAAAAA____onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("AAAAAAAAAA____onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("AAAAAAAAAA____onDetach");
    }
}

