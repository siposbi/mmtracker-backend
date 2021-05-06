# CS: GO Mm Tracker REST API

This is a REST API, which was made as an assignment for subject Kotlin-based Software Development.  
This API does not implement any security features other than logging in and registering, this means that, anyone can add and remove anything they want to.

## Installation

This product requires a working [SQL Server Express](https://www.microsoft.com/en-us/sql-server/sql-server-downloads) to be installed, but it shouldn't be difficult to replace it with any SQL Server you might like.

1. Specify your datasource URL in `application.properties`:
```
spring.datasource.url=<YOUR_DATASOURCE_URL>
```
2. Specify your SQL Server username in `application.properties`:
```
spring.datasource.username=<YOUR_USERNAME>
```
3. Specify your SQL Server password in `application.properties`:
```
spring.datasource.password=<YOUR_PASSWORD>
```
4. Run `create.sql` to set up the required schema.
5. Optionally run `data.sql`, which contains some data for presentational purposes.
6. API is ready for use  

<details>
  <summary>Example login details</summary>

| Username | Password |
|----------|----------|
| admin    | adminpw  |
| sipos    | sipospw  |
| dani     | danipw   |
</details>

Because both `create.sql` and `data.sql` contain statements to remove data, make sure to only select the statements you actually need.

## Usage

API Explorer available via Swagger UI after launch at [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)