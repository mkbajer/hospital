package com.solvd.hospital.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ConnectionPoolExample {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolExample.class);

    public static void main(String[] args) throws InterruptedException {

        ConnectionPool pool = new ConnectionPool(5);

        try (ExecutorService executor = Executors.newFixedThreadPool(7)) {

            IntStream.range(0, 7)
                    .mapToObj(i -> new ConnectionTask(pool))
                    .forEach(executor::submit);

            CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> System.out.println("CompletableFuture Task 1 running"));
            CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> System.out.println("CompletableFuture Task 2 running"));
            CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> System.out.println("CompletableFuture Task 3 running"));

            future1.join();

            executor.shutdown();
            executor.awaitTermination(8, TimeUnit.SECONDS);
        }

        LOGGER.info("All tasks completed.");
    }
}