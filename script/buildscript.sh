echo ">> begin build images for server"

pwd
docker network create load-balancer-network
docker pull amazoncorretto:21

cd ..
cd ./eurekaServer
docker build --platform linux/amd64 -t eureka-server .

cd ..

cd ./dummyServer
docker build --platform linux/amd64 -t dummy_server .
cd ..

cd ./dummyServer1
docker build --platform linux/amd64 -t dummy_server1 .
cd ..

cd ./loadBalancer
docker build --platform linux/amd64 -t load_balancer .
cd ..

docker-compose up --build


