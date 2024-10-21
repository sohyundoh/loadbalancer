FROM amazoncorretto:21
LABEL authors="sohyundoh"
WORKDIR /app
EXPOSE 8082
COPY ./build/libs/dummyServer1-0.0.0-BOILERPLATE.jar /app/dummyServer1.jar
ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-Duser.timezone=Asia/Seoul", "-jar", "/app/dummyServer1.jar"]