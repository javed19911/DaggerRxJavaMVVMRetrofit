
package com.example.dagger_rxjava_mvvm_retrofit.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dagger_rxjava_mvvm_retrofit.data.local.db.dao.NewsDao;
import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;


@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();

}
