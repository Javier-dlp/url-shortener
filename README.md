# url-shortener
URL Shortener MVP

This is a small Kotlin + Spring Boot application that shortens and resolves shortened URLs

## How to run it

1. Start the both DB and app using docker compose
```
docker-compose up -d
```
The app is now deployed on port 8080.

## Usage

It has two endpoints available in [Swagger](http://localhost:8080/swagger-ui/index.html#/url-controller):

- /shorten?url={url} GET
```
curl http://localhost:8080/shorten\?url\=http://localhost:8080/shorten?url=http://example.com
```
- /resolve?url={shortenedUrl} GET
```
curl localhost:8080/resolve?url=https://MyDomain.com/AAAAAw==
```

# Next Steps

As this is an MVP, there are several things to be done:
- For reliability:
  - Adding a DB, possibly dockerized to have it available in a local env. This is crucial in case of service failure
  - Adding control of collisions by single retrial on DB to avoid two services colliding on one URL
- For scalability:
  - Defaulting the URLs to be removed after a time period by default, and adding the possibility of having permalinks
  - Creating a custom encoder to distribute URLs on the Database, which also makes responses faster
- For usability:
  - Adding proper response bodies and message errors
  - Adding an endpoint to redirect from the shortenUrl, instead of simply resolving it

And many more! I hope this project was fun to read!
