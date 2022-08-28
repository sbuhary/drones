# Drones

## Introduction
There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

## Build & Test

### Prerequisites
- Java > 1.8 must be installed
- Maven must be installed

### Steps
- Clone the project and change the directory to drones:
```bash
git clone https://github.com/sbuhary/drones.git
cd drones
```
- Build the project (However, this is not necessary to run the application):
```bash
mvn clean package
```
- Run unit tests:
```bash
mvn test
```

## Installation

### Prerequisites
- Docker must be installed

### Steps
- Clone the project and change the directory to drones:
```bash
git clone https://github.com/sbuhary/drones.git
cd drones
```
- From the drones directory execute the below:
```bash
start docker
docker-compose up
```
- Start using the endpoints as per the documentation

## Documentation
- The Swagger UI page will then be available at: http://<server>:8080/swagger-ui/index.html
- The OpenAPI description will be available at the following url for json format: http://<server>:8080/v3/api-docs