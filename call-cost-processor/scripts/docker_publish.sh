#!/usr/bin/env bash

./gradlew build

docker build . -t "goofyahead/call-cost-processor"

docker push goofyahead/call-cost-processor:latest