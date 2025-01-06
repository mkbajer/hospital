package com.solvd.hospital.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class ConnectionPool {
    private final BlockingQueue<Connection> pool;

    public ConnectionPool(int size) {
        pool = new ArrayBlockingQueue<>(size);
        IntStream.rangeClosed(1, size)
                .mapToObj(i -> new Connection("Conn-" + i))
                .forEach(pool::offer);
    }

    public Connection acquire() throws InterruptedException {
        return pool.take();
    }

    public void release(Connection connection) {
        pool.offer(connection);
    }
}