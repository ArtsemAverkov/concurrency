package ru.clevertec;

import java.util.List;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        Client client = new Client( 10);
        List<Future<Integer>> request = client.request();
        client.summarize(request);
    }

}
