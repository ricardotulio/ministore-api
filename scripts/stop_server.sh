#!/bin/bash

LOG_FILE=/home/ec2-user/stop_server.log
APP_JAR="/home/ec2-user/ministore-api/target/app.jar"
echo "Checking if the Spring Boot application is running..." > $LOG_FILE

# Check if the application is running
if pgrep -f "java -jar $APP_JAR" > /dev/null 2>&1; then
  echo "Application is running. Attempting to stop it..." >> $LOG_FILE
  pkill -f "java -jar $APP_JAR" >> $LOG_FILE 2>&1
  if [ $? -ne 0 ]; then
    echo "Failed to stop the application" >> $LOG_FILE
    exit 1
  else
    echo "Application stopped successfully" >> $LOG_FILE
    exit 0
  fi
else
  echo "Application is not running. No action needed." >> $LOG_FILE
  exit 0
fi