#!/bin/bash

# Script to build the changes and deploy the changes into tomcat server

echo "Building the code..."
cd printkaari_base/
if mvn clean compile install ; then
    echo "Deploying changes into tomcat..."
    sudo sh /opt/tomcat/bin/shutdown.sh

    sudo rm -r /opt/tomcat/webapps/printkaari*
    sudo cp /printkaari_base/modules/rest-api/target/printkaari-api.war  /opt/tomcat/webapps/
    sudo sh /opt/tomcat/bin/startup.sh
    echo "Script completed successfully!"
else
    echo "Project build failed! Please try again."
fi