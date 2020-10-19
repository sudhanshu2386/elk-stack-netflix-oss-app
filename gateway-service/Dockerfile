FROM adoptopenjdk/openjdk14 
MAINTAINER Sudhanshu Satyam <susatyam@in.ibm.com

EXPOSE 9111   

ADD /target/gateway-service.jar gateway-service.jar 
ENTRYPOINT ["java", "-jar", "gateway-service.jar"] 