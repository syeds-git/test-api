# test-api
Simple Spring API for user registration

# How to run
Use following command to run:
``` ./mvnw spring-boot:run ```


If there is problem running the project then try following steps:


- ``` mvn clean install ```
- ``` mvn -N io.takari:maven:wrapper ```

# Notes

- The API exposes one end point which expects a username, password and an IP address
- The API throws exception if any of the fields is empty or null
- The API expects the password to be more than 8 chars long, containing at least 1 number, 1 upper case letter, 1 special character in this set “_ # $ % .”
- The API throws exception if password does not meet above criteria
- If all fields are valid the API uses the IP address to fetch the city from IP-API.com 
- If IP-API.com fails, the API will throw an exception including the reason for failure coming from IP-API.com
- The password field is being handled as text. Usually it is expected that the client will first communicate with an OAuth server via https to get a secure token.

