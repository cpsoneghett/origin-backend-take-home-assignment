# Origin Challenge API

Origin offers its users an insurance package personalized to their specific needs without requiring the user to understand anything about insurance. This allows Origin to act as their *de facto* insurance advisor.

Origin determines the user’s insurance needs by asking personal & risk-related questions and gathering information about the user’s vehicle and house. Using this data, Origin determines their risk profile for **each** line of insurance and then suggests an insurance plan (`"economic"`, `"regular"`, `"responsible"`) corresponding to her risk profile.

Thus, this project implements an API endpoint that receives a JSON payload with the user information and returns her risk profile (also as JSON response).


### Developer Environment:

**Requirements:**

1- Java Setup:
   - JDK 11+ installed;
   - Environment Variable: check if your system has the JAVA_HOME environment variable pointing to the jdk/bin folder mentioned above;
   
2- Maven Setup:
   - Maven 3.6.x installed
   - Environment Variable: check if your system has the MAVEN_HOME environment variable pointing to the maven instalation folder;
   
**Running: **

1- Clone the repository;

2- Open a cmd bash from the root directory and type the following commands:

```bash
mvn clean packege
```

3- After building and compiling, will generate the .jar file inside the target folder. Run it typing the following commands:

```bash
cd target
java -jar origin-challenge-1.0.0-SNAPSHOT.jar
```

and the webservice will run on the port 8080.

4- For making requests you can two approaches: 


   - Swagger: you can use with the request payload example below with the following link: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - Postman: make a post request to the following endpoint: [http://localhost:8080/insurance-risk/calc](http://localhost:8080/insurance-risk/calc)
   
5- Request body*:

Here is one example of a request payload to use:

```json
{
  "age": 35,
  "dependents": 2,
  "house": {"ownership_status": "owned"},
  "income": 0,
  "marital_status": "married",
  "risk_questions": [0, 1, 0],
  "vehicle": {"year": 2018}
}
```

6- Response body*:

Here is one example of a response body expected (for the input above):

```json
{
    "auto": "regular",
    "disability": "ineligible",
    "home": "owned",
    "life": "regular"
}
```

### About the code:

The code is structured by some features:

- A controller that contains the configuration of the endpoint;
- A service that contains the business rules;
- Classes that contain the objects that are traveling during requests;
- Unit tests;
- Some basic documentation config (with Swagger);
