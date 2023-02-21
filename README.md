# Spring Boot Kafka [Example]

Spring Boot Kafka with SASL/SCRAM authentication

To test this example you can run a free Kafka instance at https://www.cloudkarafka.com

## Setup

All of the authentication settings can be found in the Details page for your CloudKarafka instance

Kafka properties `/resources/application.properties`

```
spring.kafka.bootstrap-servers=${CLOUDKARAFKA_BROKERS}
spring.kafka.properties.security.protocol=SASL_SSL
spring.kafka.properties.sasl.mechanism=SCRAM-SHA-256
spring.kafka.properties.sasl.jaas.config=org.apache.kafka.common.security.scram.ScramLoginModule required username="${CLOUDKARAFKA_USERNAME}" password="${CLOUDKARAFKA_PASSWORD}";
spring.kafka.consumer.group-id=${CLOUDKARAFKA_USERNAME}-consumers

spring.kafka.consumer.auto-offset-reset=latest
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.consumer.properties.spring.json.trusted.packages=sample.kafka

cloudkarafka.topic=${CLOUDKARAFKA_USERNAME}-default
```

Run locally

```
mvn spring-boot:run -DCLOUDKARAFKA_USERNAME=<USERNAME> -DCLOUDKARAFKA_PASSWORD=<PASSWORD> -DCLOUDKARAFKA_BROKERS=<BROKERS>
```
