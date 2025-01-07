package com.solvd.hospital.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ConnectionTask implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionTask.class);
    private final ConnectionPool pool;

    public ConnectionTask(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        Connection connection = null;
        try {
            LOGGER.info("{} - Waiting for connection...", Thread.currentThread().getName());
            connection = pool.acquire();
            LOGGER.info("{} - Acquired connection with name: {}", Thread.currentThread().getName(), connection.getName());

            Thread.sleep(4000);

        } catch (InterruptedException e) {
            LOGGER.error("Connection interrupted {}", e.getMessage());
        } finally {
            if (connection != null) {
                LOGGER.info("{} - Releasing connection.", Thread.currentThread().getName());
                pool.release(connection);
            }
        }
    }
}