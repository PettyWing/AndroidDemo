package com.xyc.androidroomview.data;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * 创建一个名为WordDao的接口。
 * <p>
 * 为WordDao添加@Dao注解
 * <p>
 * 声明一个插入方法void insert(Word word);
 * <p>
 * 为上述方法添加@Insert注解，并且不需要为其提供SQL语句！（同样的用法还有@Delete and @Update）
 * <p>
 * 声明方法void deleteAll();
 * <p>
 * 这里没有方便的注解可以用于删除多个实体，因此需要用@Query注解
 * <p>
 * 还需要为@Query注解提供SQL语句@Query("DELETE FROM word_table")
 * <p>
 * 创建方法List<Word> getAllWords();
 * <p>
 * 为其添加注解与SQL@Query("SELECT * from word_table ORDER BY word ASC")
 */

@Dao
public interface WordDao {
    @Insert
    void insert(WordEntity word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    MutableLiveData<List<WordEntity>> getAllWords();
}
