#!/bin/bash

# Script to build the code with Debug Logs

echo "Building the code..."
cd ~/assessment_home/repo/assessment/assessment_base/
mvn clean install -X
echo "Script completed successfully!"

