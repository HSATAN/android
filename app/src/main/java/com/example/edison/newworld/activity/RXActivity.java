package com.example.edison.newworld.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.edison.newworld.R;
import com.example.edison.newworld.api_retrofit.Api;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Dispatcher;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Build.VERSION_CODES.O;

/**
 * Created by edison on 2017/9/5.
 */

public class RXActivity extends Activity {
    private Button button;
    private TextView textView;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.rxactivity_layout);
        button=(Button)findViewById(R.id.button_rx);
        textView=(TextView)findViewById(R.id.textView_rx);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRX();
            }
        });

    }
    public void testRX(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.106:9999/machinelearning/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        api.getInfo("huangkaijie")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>(){
                    @Override
                    public void onComplete(){

                    }
                    @Override
                    public void onSubscribe(Disposable d){
                    }
                    @Override
                    public void onError(Throwable e){
                        System.err.println(e);
                    }
                    @Override
                    public void onNext(ResponseBody responseBody){
                        try {
                            textView.setText(responseBody.string().toString());
                            System.out.println(responseBody.string().toString());

                        }catch (Exception e){
                            System.out.println(e);
                        }

                    }

                });
    }
}
