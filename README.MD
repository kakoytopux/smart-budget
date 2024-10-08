## Приложение Умный Бюджет

Это руководство для запуска приложения Умный Бюджет с использованием указанной конфигурации.

### Запуск Приложения

1. Убедится, что на устройстве установлен и запущен Docker
2. Скачать последний релиз [ссылка](https://github.com/kakoytopux/smart-budget/releases)
3. Распаковать архив, разместить `jar-файл` в одной папке с application.yaml
4. Зайти в папку docker и через консоль выполнить: docker compose up
5. Убедится, что версия java не ниже 17 (для проверки в консоли выполнить: java -version)
6. В консоли этой папки выполнить: java -jar CURRENT_JAR_FILE_NAME.jar (где CURRENT_JAR_FILE_NAME.jar - полное название актуального jar-файла из архива (например smart-budget-0.0.1-SNAPSHOT.jar))
7. Будет запущено приложение Spring, готово!

PS:
Если при запуске докер-контейнеров возникает ошибка, связанная с портами, то
1. Зайти в консоль с правами администратора и выполнить: net stop winnat
2. Если не помогло, то проверить, что другие приложения не занимают рабочих портов этого приложения
### Конфигурация Swagger
Мы не создавали фронтенд приложения, поэтому сделали документацию api swagger.
Можно зарегистрироваться или войти в аккаунт, а далее использовать полученный токен доступа (Кнопка Authorize)
Документация Swagger доступна по следующим ссылкам:
- [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Конфигурация Безопасности

Приложение включает в себя конфигурацию Spring Security для управления доступом к различным конечным точкам. Метод  `setSecurityFilterChain`  используется для настройки правил безопасности.
-  setSecurityFilterChain : Метод настройки правил безопасности.
-  csrf(AbstractHttpConfigurer::disable) : Отключение CSRF защиты.
-  authorizeHttpRequests : Установка правил доступа к конечным точкам.
-  formLogin : Настройка формы входа.

### Основные Сущности

#### TransactionDto
-  transactionSum: BigDecimal : Сумма транзакции.
-  category: String : Категория транзакции.
-  description: String : Описание транзакции.
-  bankId: Long : Идентификатор банка.
-  createdAt: LocalDateTime : Время создания транзакции.
-  username: String : Имя пользователя.
-  cashBackSum: BigDecimal : Сумма кэшбэка.

#### BalancePointDto
-  sum: BigDecimal : Сумма баланса.
-  date: LocalDateTime : Дата баланса.

#### UserDto
-  username: String : Имя пользователя.
-  password: String : Пароль пользователя.
-  accountBalance: BigDecimal : Баланс счета.

### Конечные Точки

Ниже приведены основные конечные точки, доступные в приложении:

- **POST /api/upload**: Загрузить файл.
- **POST /api/security/account/sign-up**: Зарегистрировать нового пользователя и получить токен доступа.
- **POST /api/security/account/sign-in** Авторизоваться
- **POST /api/forecast-balance-on-month**: Прогноз баланса на следующий месяц.
- **POST /api/forecast-balance-at-date**: Прогноз баланса на определенную дату.
- **POST /api/add-my-transaction**: Добавить новую транзакцию.
- **GET /isAlive**: Конечная точка проверки состояния.
- **GET /api/get-my-balance**: Получить баланс текущего пользователя.
- **GET /api/get-my-balance-in-month/{month}**: Получить баланс за определенный месяц.
- **GET /api/get-my-balance-by-category/{category}**: Получить баланс по определенной категории.
- **GET /api/get-all-my-transactions**: Получить все транзакции текущего пользователя.

### Сервисы

#### Сервис Расчета Баланса
-  `calculateTotalAmount` : Рассчитать общую сумму транзакций.
-  `calculateTotalInMonth` : Рассчитать общую сумму для определенного месяца.
-  `calculateTotalForCategory` : Рассчитать общую сумму для определенной категории.
-  `subtractCashBaskFromSum` : Вычесть кэшбэк из суммы транзакции.
-  `convertTransactionsToBalancePoints` : Преобразовать транзакции в точки баланса.

#### Сервис Транзакций
-  `getAllMyTransactions` : Получить все транзакции текущего пользователя.
-  `addMyTransactionAndUpdateBalance` : Добавить новую транзакцию и обновить баланс пользователя.

#### База Данных
- db : Сервис базы данных PostgreSQL.
- image : Образ PostgreSQL версии 12.3.
- environment : Переменные окружения для настройки базы данных.
- ports : Проброс портов для доступа к базе данных. 5433:5432

#### Мониторинг и визуализация
grafana : Сервис для мониторинга и визуализации данных.
- build : Путь к Dockerfile для сборки Grafana.
- ports : Проброс портов для доступа к Grafana в браузере. 3000:3000
- volumes : Монтирование тома для сохранения данных Grafana.
- environment : Настройки администратора Grafana.
- networks : Подключение к сети для мониторинга.

#### Мониторинг и сбор метрик
- prometheus : Сервис мониторинга и сбора метрик.
- image : Образ Prometheus для сбора метрик.
- ports : Проброс портов для доступа к интерфейсу Prometheus. 9090:9090
- volumes : Монтирование конфигурационного файла Prometheus.
- extra_hosts : Дополнительные хосты для связи.
- networks : Подключение к сети для мониторинга.

  
