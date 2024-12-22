--Build image
docker build -t identity-service:0.0.1 .
-- Create network
docker create network identity-network
-- Run MySQL
docker run --network identity-network --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
#docker run --network identity-network --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
-- Run Service
docker run --network identity-network --name identity-service -p 8080:8080 -e DB_URL=jdbc:mysql://mysql:3306/identity identity-service:0.0.1


