package com.jaison.myblog.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 12/03/17.
 */

public class Article implements Parcelable {

    @SerializedName("id")
    Integer id;

    @SerializedName("heading")
    String heading;

    @SerializedName("title")
    String title;

    @SerializedName("date")
    String date;

    @SerializedName("content")
    String content;


    public Integer getId() {
        return id;
    }


    public String getHeading() {
        return heading;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.heading);
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.content);
    }

    public Article() {
    }

    protected Article(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.heading = in.readString();
        this.title = in.readString();
        this.date = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
