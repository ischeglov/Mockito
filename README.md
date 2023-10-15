# Mockito

## Тестирование сервиса отправки сообщений

## Описание
Отправка локализованных сообщений, в котором на основе ip-адреса, переданного в заголовке, определяется язык отправляемого сообщения.
ip-адрес начинающийся со "172." относится к российскому сегменту, а с "96." - к американскому. Для российских адресов отправляется текст на русском, а для американских адресов и всех остальных - на английском.
Наша задача написать/добавить unit-тесты с использованием библиотеки mockito для проверки корректности работы функционала.

## Реализация
- Пишем тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием `Mockito`
    -  Проверяем, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
    -  Проверяем, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов.
- Пишем тесты для проверки определения локации по ip (класс GeoServiceImpl)
    -  Проверяем работу метода `public Location byIp(String ip)`
- Пишем тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
    -  Проверяем работу метода `public String locale(Country country)`
