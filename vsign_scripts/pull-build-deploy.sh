#!/bin/bash

# Script to pull changes from GIT repo, build the changes and deploy the changes into tomcat server

echo "Pulling latest changes..."
cd ~/repo/project-1i/
git checkout develop
if git pull ; then
	echo "Building the code..."
	cd ~/assessment_home/repo/assessment/assessment_base/
	if mvn clean install ; then
		echo "Deploying changes into tomcat..."
		sudo service tomcat7 stop
		sudo rm -r /var/lib/tomcat7/webapps/assessment-api*
		sudo cp ~/assessment_home/repo/assessment/assessment_base/modules/rest-api/target/assessment-api.war /var/lib/tomcat7/webapps/
		sudo service tomcat7 start
		echo "Script completed successfully!"
	else 
		echo "Project build failed! Please try again."
	fi
else 
	echo "GIT Pull got failed! Please try again."
fi

