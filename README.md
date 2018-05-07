## Prime numbers generator

Spring Boot application exposing RESTfull service which calculates and returns all the prime numbers up to and including a number provided.

## Installation/Running

### Requirements
1. java 8+
2. maven

### Build and test

`mvn clean verify`

### Run the application

`java -jar target/primenumbers-api-0.0.1-SNAPSHOT.jar `

By default runs on port 8082, which can be modified in `application.properties`

## API

* **URL**

  /primes/:limit

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `limit=[integer]` - max supported value `2 147 483 638`

* **Supported return content types**

   `application/json` | `application/xml`
 
* **Success Response:**
  
  * **Code:** 200 <br />
    **Content-Type:** application/json <br />
    **Content:** 
    
    ```json
    {
      "Initial": 10,
      "Primes": [ 2, 3, 5, 7]
    }
    ```
                  
  OR
  
    * **Code:** 200 <br />
      **Content-Type:** application/xml <br />
      **Content:** 
      
      ```xml
      <PrimesResponse>
        <Initial>10</Initial>
        <Primes>
            <Primes>2</Primes>
            <Primes>3</Primes>
            <Primes>5</Primes>
            <Primes>7</Primes>
        </Primes>
      </PrimesResponse>
      ```    
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />

  OR

  * **Code:** 500 INTERNAL SERVER ERROR <br />