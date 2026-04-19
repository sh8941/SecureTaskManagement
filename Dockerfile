FROM openjdk:22-jdk
ADD target/SecureTaskManagement.jar SecureTaskManagement.jar

ENTRYPOINT ["java", "-jar", "/SecureTaskManagement.jar"]