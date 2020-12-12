package com.example.nasa_iotd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//IOTD = Image of the Day

@Dao
public interface DateEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDateEntry(DateEntry d);

    @Query("SELECT * FROM dates WHERE date IS :date")
    public DateEntry forDate(String date);

    @Query("select * from dates")
    public List<DateEntry> getAllDateEntries();

//    @Query("select * from dates where id = :userId")
//    public List<DateEntry> getDateEntry(long userId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDateEntry(DateEntry dateEntry);

//    @Query("delete from dates")
//    void removeAllDateEntry();
//
//    @Query("delete from dates where id = :dateEntryId")
//    void removeDateEntry(long dateEntryId);
}