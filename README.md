
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

cd /home/huginn/faks/rassus/lab_prof/microservices/humidity-microservice ; gradle jibDockerBuild ; cd /home/huginn/faks/rassus/lab_prof/microservices/temperature-microservice ; gradle jibDockerBuild ;cd /home/huginn/faks/rassus/lab_prof/microservices/aggregator-microservice ; gradle jibDockerBuild;cd /home/huginn/faks/rassus/lab_prof/microservices/config-server-microservice ; gradle jibDockerBuild ; cd /home/huginn/faks/rassus/lab_prof/microservices/eureka-microservice ; gradle jibDockerBuild


docker run -p 8080:8080 aggregator-microservice ; docker run -p 8081:8081 temperature-microservice ; docker run -p 8084:8084 humidity-microservice ; docker run -p 8888:8888 config-server-microservice ; docker run -p 8761:8761 eureka-server
