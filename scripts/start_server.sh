#!/bin/bash
# Start the Spring Boot application
source /etc/environment

APP_JAR="/home/ec2-user/ministore-api/target/app.jar"
nohup sudo java -jar $APP_JAR --server.port=80 > /home/ec2-user/ministore-api.log 2>&1 &