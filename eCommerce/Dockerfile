#from which java or jdk our app will star working
FROM eclipse-temurin

#app working directory
WORKDIR /eCommerce

#copying all files to docker using jar files
COPY target/eCommerce-0.0.1-SNAPSHOT.jar app.jar

#port number for docker
EXPOSE 8081

#from where start an application in docker
# so this is the entry point and app.jar plae in jar files refer above
# use below commonds for running docker it automatically run below line inside the entry point

#java jar  app.jar
ENTRYPOINT ["java","-jar", "app.jar"]