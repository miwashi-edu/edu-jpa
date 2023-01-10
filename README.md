# edu-jpa

> Vi lägger till Java Persistency API (JPA)

## Förberedelser

```bash
docker start iths-mysql
docker exec -i iths-mysql mysql -uroot -proot < chinook.sql
docker exec -i iths-mysql mysql -uroot -proot <<< "GRANT ALL ON Chinook.* TO 'iths'@'%'"
```

## Låna kod

> Vi behöver JPA starter, och en drivrutin för MySQL

> En "starter" är alltid convention over configuration, och fungerar alltid utan att man konfigurerar den.


### ./app/build.gradle

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.30'
}
```

## Inställningar 

### ./app/src/main/resources/application.properties

> Egenskaper / Inställningar som gäller alltid (eventuellt inte i produktion)

```properties
spring.profiles.active=${ENV:dev}
server.port=${PORT:8080}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=iths
spring.datasource.password=iths
spring.datasource.url=jdbc:mysql://localhost:3306/Chinook?createDatabaseIfNotExist=true

spring.data.jpa.repositories.enabled=true

se.iths.backend.url=http://localhost:8080
```

### ./app/src/main/resources/application-dev.properties

> Egenskaper / Inställningar som bara gäller utveckling

```properties
spring.devtools.restart.enabled=true

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
logging.level.org.springframework.jdbc.core.JdbcTemplate=DEBUG
logging.level.org.springframework.jdbc.core.StatementCreatorUtils=TRACE

spring.jpa.show-sql=true
```

