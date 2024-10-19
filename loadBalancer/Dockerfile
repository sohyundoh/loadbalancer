FROM amd64/amazoncorretto:21
LABEL authors="sohyundoh"
WORKDIR /app
EXPOSE 8080
COPY ./build/libs/loadBalancer-0.0.0-BOILERPLATE.jar /app/loadBalancer.jar
ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-Duser.timezone=Asia/Seoul", "-jar", "/app/loadBalancer.jar"]