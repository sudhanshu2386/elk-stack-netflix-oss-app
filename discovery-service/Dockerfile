FROM adoptopenjdk/openjdk14 
MAINTAINER Sudhanshu Satyam <susatyam@in.ibm.com

EXPOSE 8761  

ADD /target/discovery-service.jar discovery-service.jar 
ENTRYPOINT ["java", "-jar", "discovery-service.jar"] 