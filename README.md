# airport-microservice![Java CI with Gradle](https://github.com/dechristo/airport-microservice/workflows/Java%20CI%20with%20Gradle/badge.svg)

## 1. Building and Running the Microservice

At the project's root folder type `gradlew build` to build the app and
 `gradlew bootRun` to run it.

## 2. Tests

### 2.1 Unit

Run with ~gradle `gradlew test -i`

### 2.2 Integration

## 3. Databases

##3.1 MySQL: 
1. Initialize MySQL service
2. Run query: `./db/init.sql`

##3.2 Redis: 
1. Initialize Redis service: `redis-server --requirepass root`

## 4. Endpoints

## 4.1 GET /health-check

### 4.1.1 Example Request

    localhost:4000/airports?city=Miami


### 4.1.2 Example Response (`Http 200`)

    [
      {
        "iataCode": "MIA",
        "icaoCode": "KMIA",
        "country": null,
        "region": "US-FL",
        "city": "Miami",
        "latitude": 25.7932,
        "longitude": -80.290604,
        "utcOffset": "-5",
        "timezone": "America/New_York"
      },
      {
	B
        "iataCode": "MIO",
        "icaoCode": "",
	B
        "country": null,
        "region": "",
        "city": "Miami",
        "latitude": 0.0,
        "longitude": 0.0,
        "utcOffset": "",
        "timezone": null
      },
      {
        "iataCode": "MPB",
        "icaoCode": "",
        "country": null,
        "region": "US-FL",
        "city": "Miami",
        "latitude": 0.0,
        "longitude": 0.0,
        "utcOffset": "",
        "timezone": null
      },
      {
        "iataCode": "OPF",
        "icaoCode": "KOPF",
        "country": null,
        "region": "US-FL",
        "city": "Miami",
        "latitude": 25.907,
        "longitude": -80.278397,
        "utcOffset": "-5",
        "timezone": "America/New_York"
      },
      {
        "iataCode": "TMB",
        "icaoCode": "KTMB",
        "country": null,
        "region": "US-FL",
        "city": "Miami",
        "latitude": 25.6479,
        "longitude": -80.4328,
        "utcOffset": "-5",
        "timezone": "America/New_York"
      },
      {
        "iataCode": "TNT",
        "icaoCode": "KTNT",
        "country": null,
        "region": "US-FL",
        "city": "Miami",
        "latitude": 25.861799,
        "longitude": -80.897003,
        "utcOffset": "-5",
        "timezone": "America/New_York"
      }
    ]

Is not airports are found the response is a `404`.
## 4.2 GET /airport/{airportCode}

### 4.2.1 Example Request
    localhost:4000/airports/MIA
    
### 4.2.2 Example Response

    {
      "iataCode": "MIA",
      "icaoCode": "KMIA",
      "country": null,
      "region": "US-FL",
      "city": "Miami",
      "latitude": 25.7932,
      "longitude": -80.290604,
      "utcOffset": "-5",
      "timezone": "America/New_York"
    }
## 4.3 GET /airports?[city | country]

### 4.3.1 Example Request
### 4.3.2 Example Response

## 4.4 PATCH /airports

### 4.4.1 Example Request
### 4.4.2 Example Response

## 5. Docker

### 5.1 Build And Run Single Container
To run the app with Docker, first build the container:

`docker build -t=airport-ms .`

and run it:

`docker run -p 4000:4000 airport-ms`

### 5.2  Run All containers
`docker-compose up`

### 5.3 Remove All images, containers

`docker system prune -a --volumes`

### 6. Deployment after new changes are made

If any changes to the app were done, do the following:

1. Rebuild the app with `gradlew build`
2. Rebuild the container with `docker build -t=airport-ms .`
3. Start the containers with `docker-compose up`
