# spring-application-template

## Manual build

Build command
```bash
mvn clean package
```
Test command
```bash
mvm verify
```

## Run in docker compose

Deploy application container env in docker use compose
```bash
docker-compose build
docker-compose up
```

Destroy application env
```bash
docker-compose down
```

Local application url: http://localhost:8080/swagger-ui/index.html

## GitHub pipeline description
![Pipeline](./pipeline.jpg)
