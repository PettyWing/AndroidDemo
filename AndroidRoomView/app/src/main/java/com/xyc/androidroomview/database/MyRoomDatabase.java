package com.xyc.androidroomview.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.xyc.androidroomview.data.WordDao;
import com.xyc.androidroomview.data.WordEntity;

/**
 * Created by xieyusheng on 2018/11/8.
 */

@Database(entities = {WordEntity.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "test_database";

    /**
     * 实体可以声明多个，声明的实体将在数据库中创建对应的表
     */
    public abstract WordDao wordDao();

    private static volatile MyRoomDatabase INSTANCE;

    static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}