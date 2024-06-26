# Привет, я [Артем](https://www.linkedin.com/in/артем-аверков-052657239/)

## Описание проекта

Этот проект реализует взаимодействие между клиентом и сервером в многопоточной среде, с использованием классов из пакета java.util.concurrent.

## Задание

### Клиент:
- Имеет список данных в виде List<Integer> от 1 до n.
- Отправляет запросы на сервер асинхронно, используя отдельные потоки.
- Для каждого запроса выбирается значение по случайному индексу из списка и отправляется на сервер.
- Задержка между запросами составляет от 100 до 500 мс.
- Количество запросов равно размеру первоначального списка.
- После отправки всех запросов размер списка данных должен быть равен 0.

### Сервер:
- Получает запросы от клиента.
- Метод обработки запроса имеет случайную задержку от 100 до 1000 мс.
- Использует общий ресурс типа List<Integer>, куда складывает значения, приходящие с запросом.
- Отправляет в ответ на запрос размер листа на момент формирования ответа.
- Итоговый контроль на стороне сервера: список должен содержать значения от 1 до n без пробелов и повторений, и его размерность должна быть n.

### Взаимодействие между клиентом и сервером:
- Клиент получает ответы от сервера и суммирует значения в общий для всех потоков ресурс `accumulator`.
- Контроль: `accumulator = (1+n) * (n/2)`. Например, для диапазона 1-100 ответ должен быть 5050.

### Требования
- Протестировать оба класса с проверкой многопоточности.
- Протестировать взаимодействие клиента и сервера с помощью интеграционного теста.
- Использовать классы пакета `java.util.concurrent`: Lock, Callable, Executor, Future, и другие на ваш выбор.
- Не использовать методы класса Object, относящиеся к потокам и монитору, а также ключевое слово synchronized.