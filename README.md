# SpringBoot-Kafka
#### Publishing Message -> Kafka -> Subscribe & Send To Message Action Server
##### Author 박현우
##### Create Date 2022.03.15
#
### 개발 환경
* Java11
* Spring Boot
* Docker-Compose (confluentinc/cp-zookeeper image를 통한 kafka 서버 컨테이너)
#
### 내용
* SQS만 사용해보다가 Kafka도 한번 써보고 싶어서 만든 프로젝트
* POST /publish api을 통해 message를 queue에 집어 넣는다.
* POST /subscribeStart api을 통해 [node-message-action](https://github.com/awakelife93/node-message-action)에게로 전송한다.
#
### todo
프로젝트 작업 중...
#
### 실행
* docker-compose -d
