import org.junit.jupiter.api.Test;
import ru.clevertec.Server;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {

    private final Server server = new Server();

    @Test
    void testProcessRequest() {
        // Проверка корректной обработки запроса и блокировки
        int result = server.processRequest(5); // Пример с передачей числа 5
        assertEquals(1, result); // Проверка, что после одного запроса список содержит один элемент
        // Добавьте другие тесты для проверки различных сценариев в методе processRequest
    }
}