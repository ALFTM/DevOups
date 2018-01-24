#!/bin/bash

KAFKA_HOME=/usr/local/kafka

KAFKA_CFG_FILE="$KAFKA_HOME/config/server.properties"

sed -i "s/KAFKA_BROKER_ID/$KAFKA_BROKER_ID/g" ${KAFKA_CFG_FILE}
sed -i "s/KAFKA_PARTITIONS/$KAFKA_PARTITIONS/g" ${KAFKA_CFG_FILE}
sed -i "s/KAFKA_ZK/$KAFKA_ZK/g" ${KAFKA_CFG_FILE}
sed -i "s/KAFKA_DELETE_TOPIC/$KAFKA_DELETE_TOPIC/g" ${KAFKA_CFG_FILE}
sed -i "s/KAFKA_RETENTION/$KAFKA_RETENTION/g" ${KAFKA_CFG_FILE}
sed -i "s/KAFKA_REPLICA_FETCH_MAX_BYTES/$KAFKA_REPLICA_FETCH_MAX_BYTES/g" ${KAFKA_CFG_FILE}
sed -i "s/KAFKA_MESSAGE_MAX_BYTES/$KAFKA_MESSAGE_MAX_BYTES/g" ${KAFKA_CFG_FILE}

export KAFKA_LOG_DIRS='/var/lib/kafka'
export LOG_DIR='/var/log/kafka'
export KAFKA_LOG_FILE=${LOG_DIR}/kafka-${KAFKA_BROKER_ID}.log
export JMX_PORT=19999
export KAFKA_LOG4J_OPTS="-Dlog4j.configuration=file:$KAFKA_HOME/config/log4j.properties"

exec ${KAFKA_HOME}/bin/kafka-server-start.sh ${KAFKA_CFG_FILE} >> ${KAFKA_LOG_FILE} 2>&1
