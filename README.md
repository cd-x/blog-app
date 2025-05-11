## Blog Application

---

### Application

| **Environment Variable** 	 | **Default Value** 	 | **Value**                                                         	 | **Usage**                                                     	 |
|----------------------------|---------------------|---------------------------------------------------------------------|-----------------------------------------------------------------|
| MONGODB_URI              	 | NA                	 | String: Mongo atlas driver uri                                    	 | To connect to database                                        	 |
| APP_PASSWORD             	 | NA                	 | String: Gmail App Password                                        	 | To send out emails for wekly <br>journal sentiment            	 |
| ELASTICSEARCH_URI        	 | NA                	 | String: elastic search uri e.g. <br>        http://localhost:9200 	 | To index journals and provide <br>search ability to end users 	 |
| USERNAME                   | NA                  | String: gmail username <br> from which emails will  dessiminated    | To send out emails for weekly sentiment                         |
| KAFKA_URI                  | NA                  | String: kafka host and port                                         | message queue to synchronize elasticsearch                      |

---

### Data

**Redis**
For caching read-only data like pricing of subscription

**MongoDB**

Using Mongo Atlas for storing journals over AWS host.




---

### Search

docker-compose to spin up elastic search and kibana

```yaml
services:
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.13.2
    container_name: elasticsearch
    environment:
      - node.name=es01
      - discovery.type=single-node
      - bootstrap.memory_lock=true
      - xpack.security.enabled=false
      - network.host=0.0.0.0
    ulimits:
      memlock:
        soft: -1
        hard: -1
    volumes:
      - esdata:/usr/share/elasticsearch/data
    ports:
      - 9200:9200

  kibana:
    image: docker.elastic.co/kibana/kibana:8.13.2
    container_name: kibana
    environment:
      - ELASTICSEARCH_HOSTS=http://elasticsearch:9200
    ports:
      - 5601:5601
    depends_on:
      - elasticsearch

volumes:
  esdata:
```

---

### Message Queue

Kafka in KRaft mode used to decouple ES updates from Mongo Updates

```yaml
version: '3'

services:
  kafka:
    image: bitnami/kafka:latest
    environment:
      - KAFKA_ENABLE_KRAFT=yes
      - KAFKA_CFG_NODE_ID=1
      - KAFKA_CFG_PROCESS_ROLES=broker,controller
      - KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@kafka:9093
      - KAFKA_KRAFT_CLUSTER_ID=kraft-cluster
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 # remove in prod
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093
      - KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT
      - KAFKA_CFG_INTER_BROKER_LISTENER_NAME=PLAINTEXT
      - KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER
      - KAFKA_CFG_AUTO_CREATE_TOPICS_ENABLE=true
      - ALLOW_PLAINTEXT_LISTENER=yes
    ports:
      - "9092:9092"
    volumes:
      - kafka_data:/bitnami/kafka

volumes:
  kafka_data:

```