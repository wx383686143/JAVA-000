package io.kimmking.kmq.core;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KmqQueue<T> {

    private Object[] items;

    private int size;

    private int head;
    private int end;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public KmqQueue(int size) {
        items = new Object[size];
        this.head = 0;
        this.end = -1;
        this.size = 0;
    }

    /**
     * 入列
     */
    public boolean push(T obj) {
        lock.lock();
        try {
            if (this.size > items.length) {
                notFull.await();
            }
            if (this.end == items.length - 1) {
                this.end = -1;
            }
            items[++end] = obj;
            size++;
            notFull.signal();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return false;
    }

    /**
     * 出列
     * @return
     */
    public T pop() {
        lock.lock();
        T obj = null;
        try {
            while (this.size == 0) {
                notEmpty.await();
            }
            obj = (T) items[head++];
            if (head == items.length) {
                head = 0;
            }
            size--;
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return obj;
    }

}
