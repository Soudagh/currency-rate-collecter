# Сервис получения курса валют
REST-API сервис, который позволяет получить курс валют за определённую дату с сайта центробанка.

## Функционал
+ Общение с сервисом центробанка при помощи протокола SOAP.
+ Basic-аутентификация.
+ Кэширование запросов.
+ Логирование истории запросов.

## Установка и запуск

1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/Soudagh/currency-rate-collecter.git
   cd currency-rate-collecter
   ```
 2. Добавьте следующие переменные окружения: `DATABASE_USERNAME`, `DATABASE_URL`, `DATABASE_PASSWORD`
 3. Сгенерируйте Java-классы из WSDL
  ```bash
      ./mvnw clean compile
  ```
 5. Запустите приложение через запуск в IDE или
```bash
./mvnw spring-boot:run
```

## Доступные запросы

### Регистрация пользователя
```
POST api/auth/register
```

### Получение курса валют за определённую дату 
```
GET /api/rate?date=yyyy-MM-dd
```

Пример: 
```
GET /api/rate?date=2025-04-17
```

<img width="606" alt="image" src="https://github.com/user-attachments/assets/b74983e5-46c2-405a-b0bf-314325452c5d" />
