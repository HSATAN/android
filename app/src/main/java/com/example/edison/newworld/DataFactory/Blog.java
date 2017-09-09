package com.example.edison.newworld.DataFactory;

import com.google.gson.annotations.SerializedName;

import static android.R.attr.author;

/**
 * Created by edison on 2017/9/9.
 */

public class Blog {
    @SerializedName("id")
    public long id;
    @SerializedName("date")
    public String date;
    @SerializedName("author")
    public String author;
    @SerializedName("content")
    public String content;
    @SerializedName("title")
    public String title;
    @Override
    public String toString(){
        return "Blog{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
