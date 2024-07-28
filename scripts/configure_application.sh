#!/bin/bash
# Perform any configuration steps if necessary
echo "Configuring application"
sudo setcap 'cap_net_bind_service=+ep' /home/ec2-user/ministore-api/target/app.jar