package com.solvd.hospital.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ConnectionPoolExample {
    private static final Logger log = LogManager.getLogger(ConnectionPoolExample.class);

    public static void main(String[] args) throws InterruptedException {

        ConnectionPool pool = new ConnectionPool(5);

        try (ExecutorService executor = Executors.newFixedThreadPool(7)) {

            IntStream.range(0, 7)
                    .mapToObj(i -> new ConnectionTask(pool))
                    .forEach(executor::submit);

            CompletableFuture.runAsync(() -> log.info("CompletableFuture Task 1 running"));
            CompletableFuture.runAsync(() -> log.info("CompletableFuture Task 2 running"));
            CompletableFuture.runAsync(() -> log.info("CompletableFuture Task 3 running"));

            executor.shutdown();
            executor.awaitTermination(8, TimeUnit.SECONDS);
        }

        log.info("All tasks completed.");
    }
}