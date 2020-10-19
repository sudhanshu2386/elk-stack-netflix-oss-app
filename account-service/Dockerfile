FROM adoptopenjdk/openjdk14 
MAINTAINER Sudhanshu Satyam <susatyam@in.ibm.com>

EXPOSE 9112  

ADD /target/account-service.jar account-service.jar 
ENTRYPOINT ["java", "-jar", "account-service.jar"] 