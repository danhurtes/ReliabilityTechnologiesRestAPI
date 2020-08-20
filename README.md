Тз для стажеров на вакансию разработчика

Общее требование:
Разработка rest-api сервиса, для обработки и хранений изменения статусов по транзакциям договоров, с возможностью логирования.
Описание:
Разработать сервис, принимающий на вход json объект, сохраняющий его (in-memory-db), с возможность вывести все статусы по определенному коду, модуль логирования должен быть реализован при помощи spring aop, или spring bpp, что писать в лог - опциональный выбор кандидата.
Требуемые технологии:
Spring boot 2.0, spring data(как orm), liquibase (для миграций), spring aop или spring bpp (для логирования), бд - опционально

Схема объектов :
table 1 - Codes
прим. поле code – не уникально и может повторяться
id      pk
code    int

table 2 -  Transactions
code                fk_codes_code
status              Возможные статусы : “NEW”, “PENDING”, “ACTIVE”, “STAN_BY”, “FINAL”
time                время операции - timestamp (создается при вставке обьекта)
contract_number     int

Пример входного json объекта
{“code”: 10, “contractNumber”: 802369, “status”: “NEW”, }

Дополнение:
Перед вставкой - проверить наличие code в таблице codes, при отсутствии - создать, при наличии обновить время операции.