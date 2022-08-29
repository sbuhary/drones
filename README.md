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
- The Swagger UI page will be available at: http://localhost:8080/swagger-ui/index.html
- The OpenAPI description will be available at the following url for json format: http://localhost:8080/v3/api-docs

### RegisterDrone
#### POST /api/v1/drone
![registerDrone](https://user-images.githubusercontent.com/60466231/187209064-851dd53b-496b-4f6f-aadb-48ebad665bf8.png)

### LoadDroneWithMedication
#### POST /api/v1/drone/{serial_no}/medication
![loadDroneWithMedication](https://user-images.githubusercontent.com/60466231/187209106-5ce72e24-83f8-46d2-ab84-bf948523395d.png)

### LoadedMedicationItems
#### GET /api/v1/drone/{serial_no}/medication
![loadedMedicationItems](https://user-images.githubusercontent.com/60466231/187209140-0105629c-7cba-4933-9b4b-39a2fefd8a20.png)

### CheckAvailableDrones
#### GET /api/v1/drone/available
![checkAvailableDrones](https://user-images.githubusercontent.com/60466231/187209162-a6eed24a-aaa4-4c4c-88db-17f391c85326.png)

### CheckDroneBatteryLevel
#### GET /api/v1/drone/{serial_no}/battery_level
![checkDroneBatteryLevel](https://user-images.githubusercontent.com/60466231/187209187-c78a2a31-f8cf-466a-b716-725727385080.png)
