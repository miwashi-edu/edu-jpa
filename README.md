# edu-jpa

> Vi utgår ifrån en fungerande Spring Boot applikation och lägger till JPA konfiguration mot Chinooks databas.

## Förberedelser

```bash
docker start iths-mysql
docker exec -i iths-mysql mysql -uroot -proot < chinook.sql
docker exec -i iths-mysql mysql -uroot -proot <<< "GRANT ALL ON Chinook.* TO 'iths'@'%'"

git clone 
```

## Låna kod

### ./app/build.gradle
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.30'
}
```

```
desc Artist;
+----------+--------------+------+-----+---------+-------+
| Field    | Type         | Null | Key | Default | Extra |
+----------+--------------+------+-----+---------+-------+
| ArtistId | int          | NO   | PRI | NULL    |       |
| Name     | varchar(120) | YES  |     | NULL    |       |
+----------+--------------+------+-----+---------+-------+

desc Album;
+----------+--------------+------+-----+---------+-------+
| Field    | Type         | Null | Key | Default | Extra |
+----------+--------------+------+-----+---------+-------+
| AlbumId  | int          | NO   | PRI | NULL    |       |
| Title    | varchar(160) | NO   |     | NULL    |       |
| ArtistId | int          | NO   | MUL | NULL    |       |
+----------+--------------+------+-----+---------+-------+
```

## Testa
```bash
// CREATE
curl --request POST --url http://localhost:8080/api/artist --header 'Content-Type: application/json' --data '{"id": "1", "name": "Nisse"}'

// READ
curl --request GET --url http://localhost:8080/api/artist/1

// UPDATE
curl --request PUT --url http://localhost:8080/api/artist/1 --header 'Content-Type: application/json' --data '{"id": "1","name": "Nisse"}'

// DELETE
curl --request DELETE --url http://localhost:8080/api/artist/1
```