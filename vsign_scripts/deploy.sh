#!/bin/bash

# Script to re-deploy code and restart tomcat server

echo "Deploying changes into tomcat..."
sudo service tomcat7 stop
sudo rm -r /var/lib/tomcat7/webapps/assessment-api*
sudo cp ~/assessment_home/repo/assessment/assessment_base/modules/rest-api/target/assessment-api.war /var/lib/tomcat7/webapps/
sudo service tomcat7 start
echo "Script completed successfully!"

