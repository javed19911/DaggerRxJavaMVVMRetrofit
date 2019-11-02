package com.example.dagger_rxjava_mvvm_retrofit.data.local.db.dao;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dagger_rxjava_mvvm_retrofit.data.model.others.News;

import java.util.List;
import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface NewsDao {

    @Insert(onConflict = REPLACE)
    void insert(News news);

    @Insert(onConflict = REPLACE)
    void insertAll(List<News> newsList);

    @Query("SELECT * FROM NEWS")
    Single<List<News>> loadAll();

    @Query("DELETE FROM NEWS")
    public void Truncate();



}
