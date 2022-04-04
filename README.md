# pokedex
Fun Pokemon APIs. This application is built using Java 11 and Spring Boot 2.x

### How to run?
- Pre-requisite: To run this application, a Java 11 runtime is required. Ideally, install adoptopenjdk 11 from this [link](https://adoptopenjdk.net/installation.html)

#### Without container:
- Navigate to base directory of the project and run `./gradlew bootRun`
- Allow about 30 seconds for the application to start.

#### Within Docker container:
- Navigate to the base directory of the project
- Then build application's runnable jar to be packaged in the docker image by running `./gradlew clean build`  
- Build the docker image by running `docker build -t shankulk/pokedex .`
- Now run this docker image `docker run -d -p 8080:8080 shankulk/pokedex:latest`

### Available API Endpoints:
1. `http://localhost:8080/pokemon/{pokemon_name}`  
   
   This API endpoint returns basic Pokemon information. Just pass the Pokemon name as a path parameter. If the Pokemon is not found, a 404 error response is returned.   
   eg: 
   ```shell
    curl --location --request GET 'localhost:8080/pokemon/zubat'
   ```
2. `http://localhost:8080/pokemon/translated/{pokeman_name}`  
This API endpoint returns pokemon information along with translated description.  
   There are three cases:
   - `Cave Habitat`: If habitat is cave, Yoda translation is returned.
   - `Other Habitat`: If habitat is not cave, Shakespeare translation is returned.
   - `Error in translation`: If there are errors while fetching translations, an original description is returned.  
    eg:  
     ```shell
      curl --location --request GET 'localhost:8080/pokemon/translated/zubat'
      curl --location --request GET 'localhost:8080/pokemon/translated/caterpie'
     ```
     
### Things to note
- [Translation API](https://funtranslations.com/api) used in this app has a rate limit of 5 requests per hour. To get around this restriction, I've implemented simple in-memory caching on the external API endpoints.
Caching is enabled by default when the application starts, however it can be selectively turned off by toggling a feature flag. Just set the environment variable `CACHING_ENABLED` to `false` if running outside the container (ensure that the envvar is read by the app) or run the docker container using following command
    ```shell
     docker run -d -e CACHING_ENABLED=false -p 8080:8080 shankulk/pokedex:latest
    ```
- The App was developed over three days in bits and pieces. Sometimes, development time was restricted by the translation API rate limiter. Github has all the commit history.

### More things to be added to make the app production-ready:
- Instead of two API endpoints, a query parameter can be used to indicate whether translation is required or not.
- Builder pattern for domain object creation to make code more readable and cleaner.
- Separate DTO and Domain objects. Mapper library for easy mapping. 
- Integration tests around caching and functionalities.
- Open API specification/Swagger documentation