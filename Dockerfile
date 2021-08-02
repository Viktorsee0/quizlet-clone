FROM maven:latest AS build
COPY . /quizlet-clone
WORKDIR /quizlet-clone
RUN mvn clean package -DskipTests

FROM openjdk:latest
COPY --from=build /quizlet-clone/target/quizlet-clone-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","application.jar"]