# edu-jpa

> Vi utgår ifrån en fungerande Spring Boot applikation och lägger till JPA konfiguration mot Chinooks databas.

## Förberedelser

```bash
docker start iths-mysql
docker exec -i iths-mysql mysql -uroot -proot < chinook.sql
docker exec -i iths-mysql mysql -uroot -proot <<< "GRANT ALL ON Chinook.* TO 'iths'@'%'"
```

## Låna kod

### ./app/build.gradle
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'    
}
```


