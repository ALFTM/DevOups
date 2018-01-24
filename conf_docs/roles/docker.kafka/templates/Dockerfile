FROM openjdk:8-jre

RUN ["groupadd", "--gid", "{{kafka.gid}}", "-r", "kafka"]
RUN ["useradd", "--uid", "{{kafka.uid}}", "-r", "-g",  "kafka", "kafka"]

RUN apt-get update \
    && apt-get install -y \
        curl \
        tar \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p {{ kafka.download_dir }}

WORKDIR {{ kafka.download_dir }}

RUN curl --stderr /dev/null -O {{kafka.url}}

RUN tar xzvf {{ kafka.file }} \
    && mv {{kafka.package_name}} /usr/local/kafka \
    && chown -R kafka:kafka /usr/local/kafka

WORKDIR /usr/local/bin

RUN ["rm", "-Rf", "{{ kafka.download_dir }}/*"]

ENV KAFKA_ZK localhost:2181
ENV KAFKA_DELETE_TOPIC true
ENV KAFKA_BROKER_ID 1
ENV KAFKA_PARTITIONS 1
ENV KAFKA_RETENTION 168
ENV KAFKA_REPLICA_FETCH_MAX_BYTES 104857600
ENV KAFKA_MESSAGE_MAX_BYTES 104857600

RUN ["mkdir", "-p", "/var/lib/kafka", "/var/log/kafka"]
RUN ["chown", "-R", "kafka:kafka", "/var/lib/kafka", "/var/log/kafka"]

COPY kafka.sh /usr/local/bin/
RUN ["chmod", "+x", "/usr/local/bin/kafka.sh"]

COPY server.properties /usr/local/kafka/config/
RUN ["chown", "kafka:kafka", "/usr/local/kafka/config/server.properties"]

VOLUME ["/var/lib/kafka", "/var/log/kafka"]

EXPOSE 9092
EXPOSE 19999

USER kafka
ENTRYPOINT ["./kafka.sh"]
