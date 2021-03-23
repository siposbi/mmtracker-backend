# CSGO Mm Tracker REST API

This is a REST API, which was made as an assignment for subject Kotlin-based Software Development.

## Installation

This product requires a working [SQL Server Express](https://www.microsoft.com/en-us/sql-server/sql-server-downloads) to be installed, but it shouldn't be difficult to replace it with any SQL Server you might like.

1. Specify your SQL Server username in `application.properties`:
```
spring.datasource.username=<YOUR_USERNAME>
```
2. Specify your SQL Server password in `application.properties`:
```
spring.datasource.password=<YOUR_PASSWORD>
```
3. Run `create.sql` to set up the required schema.
4. Optionally run `data.sql`, which contains some data for presentational purposes.

Because both `create.sql` and `data.sql` contain statements to remove data, make sure to only select the statements you actually need.

## Usage

Documentation available via Swagger UI after launch at [localhost:8080](http://localhost:8080/swagger-ui/index.html#/)