package com.example.mvvm_1.getNumber;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class DataGenrater extends ViewModel {
    public static final String TAG="DataGenrater";
    //private String mRandomGenrater;
    private MutableLiveData<String> mRandomGenrater;
    public MutableLiveData<String> grtNumber()
    {
        if(mRandomGenrater==null)
        {
            mRandomGenrater=new MutableLiveData<>();
            createNumber();
        }
        return mRandomGenrater;
    }

    public void createNumber() {
        Random random=new Random();
       // mRandomGenrater="Number = "+random.nextInt(10-1)+1;
        mRandomGenrater.setValue("Number = "+random.nextInt(10-1)+1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
