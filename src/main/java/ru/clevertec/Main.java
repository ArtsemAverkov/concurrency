package ru.clevertec;

import java.util.List;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        int n = 100;
        Client client = new Client(n);
        List<Future<Integer>> request = client.request();
        client.summarize(request);
    }

}
