#!/bin/bash

# Script to build the changes and deploy the changes into tomcat server

echo "Building the code..."
cd ~/assessment_home/repo/assessment/assessment_base/
if mvn clean install ; then
    echo "Deploying changes into tomcat..."
    sudo service tomcat7 stop
    sudo rm -r /var/lib/tomcat7/webapps/yeet*
    sudo cp ~/assessment_home/repo/assessment/assessment_base/modules/rest-api/target/assessment-api.war /var/lib/tomcat7/webapps/
    sudo service tomcat7 start
    echo "Script completed successfully!"
else
    echo "Project build failed! Please try again."
fi
