package com.example.nasa_iotd;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DateEntry.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static com.example.nasa_iotd.AppDatabase INSTANCE;


    public abstract DateEntryDao dateEntryDao();

    public static com.example.nasa_iotd.AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, com.example.nasa_iotd.AppDatabase.class, "nasa_iotd_database")
                            // To simplify the exercise, allow queries on the main thread.
                            // Don't do this on a real app!
                            .allowMainThreadQueries()
                            // recreate the database if necessary
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}