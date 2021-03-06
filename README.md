<h1>KRAMS</h1>
<h4>Spring Data JPA with H2 DataBase and Spring Boot</h4>
<hr>

<h2>Build</h2>

```
$ mvn clean install -P dev|test
```

<h2>Documentation </h3>

- [Swagger](http://localhost:8080/swagger-ui.html)
- [Tests](https://reflectoring.io/spring-boot-data-jpa-test/)

<h2>Filter Query Examples</h2>

```bash
# list all persons
curl 'localhost:8080/persons'

# single parameter filter
curl 'localhost:8080/persons/search?age=42'

# complex filter query with multiple request parameters
curl curl 'localhost:8080/persons/search?city=Berlin&age=42'

# jpa criteria api
curl 'localhost:8080/persons/criteria?id=1&name=Alice&type=Sport'

```
