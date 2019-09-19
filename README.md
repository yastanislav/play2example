# play2example

### Исходные данные ###
За основу взят проект-пример https://github.com/playframework/play-samples/tree/2.7.x/play-java-rest-api-example
Требования под которые он был модифицирован: 
1. h2 в режиме совместимости с PostgreSQL
2. rest-сервис со swagger-описанием
3. в качестве JPA - MyBatis3
4. для миграций БД использовать механизм evolution

### Требования к окружению ###

1. Установленный sbt
2. Установленный JDK8
3. h2 дистрибутив для контроля и проверки sql-запросов
4. 

### Сборка и запуск приложения ###
sbt run