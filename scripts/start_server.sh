#!/bin/bash
# Start the Spring Boot application
APP_JAR="/home/ec2-user/ministore-api/target/app.jar"
nohup java -jar $APP_JAR > /home/ec2-user/ministore-api.log 2>&1 &