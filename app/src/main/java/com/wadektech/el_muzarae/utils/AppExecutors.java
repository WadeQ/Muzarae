package com.wadektech.el_muzarae.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static final Object LOCK = new Object();
    private static AppExecutors execInstance ;
    private final Executor diskIO;
    private final Executor mainThread ;
    private final Executor networkIO ;

    public AppExecutors(Executor diskIO, Executor mainThread, Executor networkIO) {
        this.diskIO = diskIO;
        this.mainThread = mainThread;
        this.networkIO = networkIO;
    }

    public synchronized static AppExecutors getInstance(){
        if (execInstance == null){
            synchronized (LOCK){
                if (execInstance == null){
                    execInstance = new AppExecutors(
                            Executors.newSingleThreadExecutor(),
                            Executors.newFixedThreadPool(3),
                            new MainThreadExecutor()
                    );
                }
            }
        }
        return execInstance;
    }

    public Executor diskIO(){
        return diskIO ;
    }

    public Executor mainThread(){
        return mainThread;
    }

    public Executor networkIO(){
        return networkIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable runnable) {
            handler.post(runnable);
        }
    }
}
