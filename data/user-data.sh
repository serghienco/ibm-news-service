#!/bin/bash
# install updates
yum update -y

# update java
yum install java-1.8.0 -y
yum remove java-1.7.0-openjdk

# create the working directory
mkdir /opt/ibm-news-service

# download the maven artifact from S3
aws s3 cp s3://jars.serghienco.com/news-service-0.0.1-SNAPSHOT.jar /opt/ibm-news-service/ --region=us-east-1

# create a springboot user to run the app as a service
useradd springboot

# springboot login shell disabled
chsh -s /sbin/nologin springboot
chown springboot:springboot /opt/ibm-news-service/news-service-0.0.1-SNAPSHOT.jar
chmod 500 /opt/ibm-news-service/news-service-0.0.1-SNAPSHOT.jar

java -jar /opt/ibm-news-service/news-service-0.0.1-SNAPSHOT.jar \
--spring.profiles.active=prod \
--spring.datasource.url=${IBM_URL} \
--spring.datasource.username=${IBM_USER} \
--spring.datasource.password=${IBM_PASS} \
&
