#!/usr/bin/env bash

docker ps -a | grep 'Exited' | awk '{print $1}'| xargs --no-run-if-empty docker rm -v
docker ps -a | grep 'day[s]* ago\s*Created' | awk '{print $1}'| xargs --no-run-if-empty docker rm -v
docker volume ls -qf dangling=true | xargs --no-run-if-empty -r docker volume rm
docker images | grep "<none>" | awk '{print $3}' | xargs --no-run-if-empty docker rmi
docker images | awk 'length($2)==40 {print $3}' | xargs --no-run-if-empty docker rmi
