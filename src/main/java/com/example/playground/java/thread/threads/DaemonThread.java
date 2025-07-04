package com.example.playground.java.thread.threads;

import static com.example.playground.java.thread.util.ThreadLogger.*;

public class DaemonThread extends Thread{
    private final int timeSleep;

    public DaemonThread(boolean isDaemon, int timeSleep) {
        this.timeSleep = timeSleep;
        if(isDaemon) {
            setDaemon(true); // 데몬스레드
            setName("DaemonThread");
        }

    }

    @Override
    public void run() {
        log("run() isDaemon=" + this.isDaemon());
        try {
            Thread.sleep(timeSleep); // 10초간 실행
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log("run() end");

        System.out.println(Thread.currentThread().getName() + ": run() end");
    }
}

