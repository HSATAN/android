package com.example.edison.newworld.api_retrofit;

import java.util.Map;
import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

import static android.R.attr.id;

/**
 * Created by edison on 2017/9/7.
 */

public interface Api {
    @GET("{id}")
    Call<ResponseBody> getName(@Path("id") int id);

    @POST("userinfo")
    @FormUrlEncoded
    Call<ResponseBody>getUserInfo(@Field("username") String name, @Field("password") String password);

    @POST("userinfo")
    @FormUrlEncoded
    Call<ResponseBody>getMap(@FieldMap Map<String,String> map);

    @POST("file")
    @Multipart
    Call<ResponseBody> uploadFile(@Part("name") RequestBody name, @Part MultipartBody.Part file);
    //@Part在服务器中的字段名称就是name，file在服务器端的字段是在创建file的时候设置的，@Part

}

