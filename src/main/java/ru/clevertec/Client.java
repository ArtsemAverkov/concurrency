package ru.clevertec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Client {
    private final Server server;
    private final List<Integer> data;
    private final AtomicInteger accumulator;
    private final ExecutorService executor;

    public List<Integer> getData() {
        return data;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public AtomicInteger getAccumulator() {
        return accumulator;
    }


    public Client(int n) {

        accumulator = new AtomicInteger(0);
        data = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            data.add(i);
        }
        executor = Executors.newFixedThreadPool(data.size());
        server = new Server();
    }


    public List<Future<Integer>> request() {
        return data.parallelStream()
                .map(i -> (Callable<Integer>) () -> server.processRequest(i))
                .map(executor::submit)
                .collect(Collectors.toList());
    }

    public void summarize(List<Future<Integer>> futures) {
        for (Future<Integer> future : futures) {
            try {
                accumulator.addAndGet(future.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        int expectedSum = (1 + futures.size()) * (futures.size() / 2);
        int actualSum = accumulator.get();
        if (actualSum != expectedSum) {
            System.out.println("Ошибка: ожидаемая сумма = " + expectedSum + ", сумма = " + actualSum);
        } else {
            System.out.println("Успех: ожидаемая сумма = " + expectedSum + ", сумма = " + actualSum);
        }
        executor.shutdown();
    }

}


