# Spring Data Flow Sample

| Table of content|
| ------------------- |
| [1. Running the project](#running-the-project)|
| - [1.1 Local](#running-locally)|
| - [1.2 Cloud](#running-on-aws/eks)|

## Running the project

### Running locally

```
https://github.com/simplesteph/kafka-stack-docker-compose
```

Go to the folder of each service and build then build the docker image and run it:

```
./gradlew build

docker build . --tag name:version

docker run name:version
```

#### Check messages on kafka topics

```
/bin# kafka-console-consumer --topic call-detail --bootstrap-server localhost:9092
```

### Running on AWS/EKS

#### Cheat sheets

```
https://kubernetes.io/docs/reference/kubectl/cheatsheet/
```

#### Create an AMI with permission to manage EKS

???

#### create a cluster

```
eksctl create cluster \
--name prod \
--version 1.13 \
--nodegroup-name standard-workers \
--node-type t3.medium \
--nodes 3 \
--nodes-min 1 \
--nodes-max 4 \
--node-ami auto
```

### deploy with helm  https://dataflow.spring.io/docs/installation/kubernetes/helm/

```
helm init
```

### add kubernetes dashboard https://docs.aws.amazon.com/eks/latest/userguide/dashboard-tutorial.html

```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
```

### start proxy to see it

```
kubectl proxy --port=8080 --address='0.0.0.0' --disable-filter=true &
```

### create a user and get the token

```
https://docs.aws.amazon.com/eks/latest/userguide/dashboard-tutorial.html
```

### start kafka and zookeper locally https://github.com/simplesteph/kafka-stack-docker-compose

```
docker-compose -f zk-single-kafka-single.yml up
```