package ru.clevertec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    public  final List<Integer> sharedList = new ArrayList<>();
    private  final ReentrantLock lock = new ReentrantLock();


    public  int processRequest(int i) {
        int delay = new Random().nextInt(901) + 100;
        lock.lock();
        try {
            Thread.sleep(delay);
            sharedList.add(i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return sharedList.size();
    }

}


