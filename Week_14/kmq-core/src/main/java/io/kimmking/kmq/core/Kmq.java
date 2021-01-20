package io.kimmking.kmq.core;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new LinkedBlockingQueue(capacity);
        this.mqQueue = new KmqQueue<>(capacity);
    }

    private String topic;

    private int capacity;

    private LinkedBlockingQueue<KmqMessage> queue;

    private KmqQueue<KmqMessage> mqQueue;

    public boolean send(KmqMessage message) {
//        return queue.offer(message);
        return mqQueue.push(message);
    }

    public KmqMessage poll() {
//        return queue.poll();
        return mqQueue.pop();
    }

    @SneakyThrows
    public KmqMessage poll(long timeout) {
        return queue.poll(timeout, TimeUnit.MILLISECONDS);
    }

}
