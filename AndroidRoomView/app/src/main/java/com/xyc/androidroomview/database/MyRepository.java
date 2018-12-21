package com.xyc.androidroomview.database;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.xyc.androidroomview.data.WordDao;
import com.xyc.androidroomview.data.WordEntity;

import java.util.List;

/**
 * Repository是一个可访问多数据源的类。它并非构架组件库中的一部分，
 * 但它是代码分离和体系结构的最佳实践建议。Repository用于处理数据操作，它为应用提供数据访问接口。
 */

public class MyRepository {

    private WordDao mWordDao;
    private MutableLiveData<List<WordEntity>> mAllWords;

    public MyRepository(Application application) {
        initWordTable(MyRoomDatabase.getDatabase(application));
    }

    /**
     * 初始化word表
     */
    private void initWordTable(MyRoomDatabase db) {
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    /**
     * 获取所有的word
     * @return
     */
    public MutableLiveData<List<WordEntity>> getAllWords() {
        return mAllWords;
    }

    /**
     * 使用AsyncTask来执行插入，确保其是在非UI线程中执行
     * @param word
     */
    public void insert(WordEntity word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<WordEntity, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final WordEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
