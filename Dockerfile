# Используйте официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Установите рабочую директорию
WORKDIR /app

# Скопируйте jar-файл в контейнер
COPY target/JournalAPI-0.0.1-SNAPSHOT.jar app.jar

# Откройте порт приложения
EXPOSE 8080

# Запустите приложение
ENTRYPOINT ["java", "-jar", "app.jar"]