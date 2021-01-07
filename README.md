
# Build docker images

- Build humidity-microservice
```
cd /home/huginn/faks/rassus/lab_prof/microservices/humidity-microservice ; gradle jibDockerBuild
docker run -p 8084:8084 humidity-microservice
```

- Build temperature-microservice
```
cd /home/huginn/faks/rassus/lab_prof/microservices/temperature-microservice ; gradle jibDockerBuild
docker run -p 8081:8081 temperature-microservice
```

# Cloud config server authentication

```
1. Create environment variables gitUsername and gitPassword
2. check application.properties file in config-server-microservice
It will take values from config/application.yml file 
```
