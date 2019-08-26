#!/usr/bin/env bash

./gradlew build

docker build . -t "goofyahead/call-cost-logger"

docker push goofyahead/call-logger:latest