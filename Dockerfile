# Etapa 1: build da aplicação
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Gera o .jar da aplicação
RUN mvn clean package -DskipTests

# Etapa 2: imagem leve com o JAR gerado
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o JAR da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Porta exposta pela aplicação
EXPOSE 8080

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
