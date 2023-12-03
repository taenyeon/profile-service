docker pull --platform linux/amd64 mysql
docker run --platform -d -p 3306:33306 -e MYSQL_ROOT_PASSWORD=1234 --name mysql mysql