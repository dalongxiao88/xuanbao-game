package org.come.until;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadPoolUntil
{
    private static ExecutorService fixedThreadPool;
    
    public static ExecutorService getThreadPoolUntil() {
        if (ThreadPoolUntil.fixedThreadPool == null) {
            return ThreadPoolUntil.fixedThreadPool = Executors.newFixedThreadPool(100);
        }
        return ThreadPoolUntil.fixedThreadPool;
    }
    
    public static void execute(Runnable command) {
        ThreadPoolUntil.fixedThreadPool.execute(command);
    }
    
    public static ExecutorService getFixedThreadPool() {
        return ThreadPoolUntil.fixedThreadPool;
    }
    
    public static void setFixedThreadPool(ExecutorService fixedThreadPool) {
        ThreadPoolUntil.fixedThreadPool = fixedThreadPool;
    }
    
    public static void closeFixedThreadPool(ExecutorService fixedThreadPool) {
        if (fixedThreadPool.isTerminated()) {
            fixedThreadPool.shutdown();
        }
    }
}
