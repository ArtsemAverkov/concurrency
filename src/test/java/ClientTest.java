import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.clevertec.Client;
import ru.clevertec.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ClientTest {

    @Mock
    private Server server;

    @Mock
    ExecutorService executorService;

    @BeforeEach
    void setUp() {
    executorService = mock(ExecutorService.class);
        server = mock(Server.class);
    }

    @Test
    void testConstructor() {
        int n = 5;
        Client client = new Client( n);
        assertEquals(n, client.getData().size());
    }

    @Test
    void testRequest() {
        List<Integer> data = List.of(1, 2, 3);
        Client client = new Client(3);
        when(server.processRequest(anyInt())).thenReturn(1);
        List<Future<Integer>> result = client.request();
        assertEquals(data.size(), result.size());
    }

    @Test
    void testSummarize() throws ExecutionException, InterruptedException {
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Future<Integer> future = mock(Future.class);
            when(future.get()).thenReturn(i);
            futures.add(future);
        }
        Client client = new Client(5);
        client.summarize(futures);
        for (Future<Integer> future : futures) {
            verify(future).get();
        }
        AtomicInteger accumulator = new AtomicInteger(0);
        for (int i = 1; i < 5; i++) {
            accumulator.addAndGet(i);
        }
        int expectedSum = accumulator.get();
        int actualSum = client.getAccumulator().get();

        assertEquals(expectedSum, actualSum);
    }
}
