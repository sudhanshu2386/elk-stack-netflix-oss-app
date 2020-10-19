FROM adoptopenjdk/openjdk14 
MAINTAINER Sudhanshu Satyam <susatyam@in.ibm.com

EXPOSE 9114  

ADD /target/product-service.jar product-service.jar 
ENTRYPOINT ["java", "-jar", "product-service.jar"] 