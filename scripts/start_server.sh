#!/bin/bash
# Start the Spring Boot application
source /etc/environment
echo "Spring Datasource: $SPRING_DATASOURCE_URL" >> /home/ec2-user/variables.log
echo "Postgres User: $POSTGRES_USER" >> /home/ec2-user/variables.log
echo "Postgres Password: $POSTGRES_PASSWORD" >> /home/ec2-user/variables.log

APP_JAR="/home/ec2-user/ministore-api/target/app.jar"
nohup java -jar $APP_JAR > /home/ec2-user/ministore-api.log 2>&1 &