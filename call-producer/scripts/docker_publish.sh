#!/usr/bin/env bash

./gradlew build

docker build . -t "goofyahead/call-producer"

docker push goofyahead/call-producer:latest