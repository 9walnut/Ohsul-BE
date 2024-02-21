
# jdk17 Image Start
FROM openjdk:17

# 인자 설정 - JAR_File
ARG JAR_FILE=build/libs/*.jar

# jar 파일 복제
COPY ${JAR_FILE} app.jar
COPY etc/letsencrypt/live/ohsul.site/keystore.p12 src/main/resources/keystore.p12
COPY src/main/resources/application-dev.yml application-dev.yml

# 실행 명령어
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "app.jar"]