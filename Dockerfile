FROM java

ADD ./build/libs/perpule_api.war /app/
ADD jetty-runner-8.1.9.v20130131.jar /
EXPOSE 9000
CMD ["java","-jar","jetty-runner-8.1.9.v20130131.jar","--port","9000","/app/perpule_api.war"]
