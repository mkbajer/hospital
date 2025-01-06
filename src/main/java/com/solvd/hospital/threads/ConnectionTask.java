package com.solvd.hospital.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ConnectionTask implements Runnable {
    private static final Logger log = LogManager.getLogger(ConnectionTask.class);
    private final ConnectionPool pool;

    public ConnectionTask(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            log.info("{} - Waiting for connection...", Thread.currentThread().getName());
            Connection connection = pool.acquire();
            log.info("{} - Acquired connection with name: {}", Thread.currentThread().getName(), connection.getName());

            Thread.sleep(6000);

            log.info("{} - Releasing connection.", Thread.currentThread().getName());
            pool.release(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}