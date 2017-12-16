# Chief Analytics
This is a microservice architecure project. To run the project please run each microservice individually.
## Build and Run
For this branch to function, you need to run 'interpreter' Spring Boot, Apache Kafka (with minimal ZooKeeper) and the front end 'viewservice' Express server and 'viewservice/app' ReactJS application.

#### To run 'kafka' (needs JDK >= 7):
'kafka/bin/zookeeper-server-start.sh config/zookeeper.properties'
'kafkabin/kafka-server-start.sh config/server.properties'

#### To run 'interpreter' (needs JDK >= 8):
'interpreter/gradlew bootRun'

#### To run 'viewservice':
'cd viewservice'
'npm start'

#### To run 'app':
'cd viewservice/app'
'yarn start'

### Microservices:
1. configserver
2. authervice
3. interpreter
4. viewservice
