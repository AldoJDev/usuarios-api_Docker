# Usar imagem oficial do OpenJDK 21
FROM openjdk:21-jdk-slim

# Definir diretório de trabalho
WORKDIR /app

# Copiar arquivo pom.xml e baixar dependências
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Dar permissão de execução ao mvnw
RUN chmod +x ./mvnw

# Baixar dependências
RUN ./mvnw dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Compilar a aplicação
RUN ./mvnw clean package -DskipTests

# Expor a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "target/usuario-api-0.0.1-SNAPSHOT.jar"]