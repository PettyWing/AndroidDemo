package com.xyc.androidroomview.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.xyc.androidroomview.data.WordEntity;
import com.xyc.androidroomview.database.MyRepository;

import java.util.List;

/**
 * Created by xieyusheng on 2018/11/8.
 */

public class WordViewModel extends AndroidViewModel {

    private MyRepository mRepository;

    private MutableLiveData<List<WordEntity>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new MyRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public MutableLiveData<List<WordEntity>> getAllWords() {
        return mAllWords;
    }

    public void insert(WordEntity word) {
        mRepository.insert(word);
    }
}