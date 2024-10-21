FROM amazoncorretto:21
LABEL authors="sohyundoh"
WORKDIR /app
EXPOSE 8761
COPY ./build/libs/eurekaServer-0.0.0-BOILERPLATE.jar /app/eurekaServer.jar
ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-Duser.timezone=Asia/Seoul", "-jar", "/app/eurekaServer.jar"]