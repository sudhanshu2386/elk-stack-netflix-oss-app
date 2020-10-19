FROM adoptopenjdk/openjdk14 
MAINTAINER Sudhanshu Satyam <susatyam@in.ibm.com

EXPOSE 9113  

ADD /target/customer-service.jar customer-service.jar 
ENTRYPOINT ["java", "-jar", "customer-service.jar"] 