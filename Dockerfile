# Giai đoạn 1: Build ứng dụng
FROM maven:3.9.9-amazoncorretto-21 AS builder
LABEL authors="thuan"
# Thiết lập thư mục làm việc
WORKDIR /app
COPY pom.xml .
run mvn dependency:go-offline -B
# Copy toàn bộ mã nguồn
COPY src ./src
# Build ứng dụng
RUN mvn clean package -DskipTests
# Giai đoạn 2: Tạo image runtime
FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
# Expose cổng (tuỳ chọn, ví dụ 8080 cho Spring Boot)
EXPOSE 8080
# Khởi chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]