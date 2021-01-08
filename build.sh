#!/bin/bash

curr_dir=$(pwd)

echo Build images

echo Building humidity
cd $curr_dir/microservices/humidity-microservice ; gradle jibDockerBuild
echo Building temperature
cd $curr_dir/microservices/temperature-microservice ; gradle jibDockerBuild
echo Building aggregator
cd $curr_dir/microservices/aggregator-microservice ; gradle jibDockerBuild
echo Building config server
cd $curr_dir/microservices/config-server-microservice ; gradle jibDockerBuild 
echo Building eureka
cd $curr_dir/microservices/eureka-server ; gradle jibDockerBuild
cd $curr_dir
