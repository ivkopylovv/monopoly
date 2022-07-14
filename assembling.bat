powershell ./gradlew build

docker build -t app-springboot .

docker system prune -f

docker-compose up