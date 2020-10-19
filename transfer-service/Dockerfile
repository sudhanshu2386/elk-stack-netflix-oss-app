FROM adoptopenjdk/openjdk14 
MAINTAINER Sudhanshu Satyam <susatyam@in.ibm.com

EXPOSE 9115  

ADD /target/transfer-service.jar transfer-service.jar 
ENTRYPOINT ["java", "-jar", "transfer-service.jar"] 