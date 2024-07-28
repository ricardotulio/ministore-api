#!/bin/bash
LOG_FILE=/home/ec2-user/validate_service.log
APP_JAR="/home/ec2-user/ministore-api/target/app.jar"
echo "Validating if the Spring Boot application is running..." > $LOG_FILE

# Check if the application is running
if pgrep -f "sudo java -jar $APP_JAR --server.port=80" > /dev/null 2>&1; then
  echo "Application is running." >> $LOG_FILE
  exit 0
else
  echo "Application is not running." >> $LOG_FILE
  exit 1
fi