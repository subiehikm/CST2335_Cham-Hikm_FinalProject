package com.example.nasa_iotd;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "dates", indices={@Index(value={"date"}, unique = true)})
public class DateEntry {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "explanation")
    public String explanation;

    @ColumnInfo(name = "hdurl")
    public String hdurl;

    @ColumnInfo(name = "media_type")
    public String media_type;

    @ColumnInfo(name = "service_version")
    public String service_version;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "url")
    public String url;


}
