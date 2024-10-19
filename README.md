# Load Balancer Boiler plate
해당 어플리케이션은 LoadBalancer와 내부의 Dummy Server를 구축한 BoilerPlate 프로젝트입니다.
## 사용 방법 

git clone을 받은 뒤 해당 어플리케이션 폴더에서 다음 명령어를 입력해주세요.
```shell
cd script

./buildscript.sh
```
해당 어플리케이션은 Docker 기반으로 구성되어 있습니다. 따라서, 스크립트 파일에서 실행되는 명령어에 맞춰 이미지를 빌드하고 docker-compose를 실행하여야 합니다.


## 구조
서비스 구조는 아래와 같습니다.
![img.png](img.png)

요청을 전달할 서버가 추가되고, 제거되는 상황을 대비하기 위해 Eureka Server를 구성하였습니다. 

각각 아래의 포트에서 실행되고 있습니다.
- LoadBalancer : 8080
- dummy server : 8081, 8082
- eureka server : 8761 

## 요청 흐름
1. Client에게 (`{service_url}/test/{id}`로) 요청이 들어옴
2. 로드밸런서 서버가 이를 받아 1차적으로 CACHE에 해당 요청에 해당하는 값이 있는지 찾음
3. 없다면 DummyServer로 요청을 넘김 ( 이 때, 스레드 점유로 인한 서버 마비를 막기 위해 요청을 받아오는 time out을 5초로 설정)
4. 요청을 받아온 로드밸런서 서버가 캐시에 해당 정보를 저장하고, 클라이언트에게 응답을 반환