add dependancy in pom.xml

```
<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency>
```

Add JwtUtil class code generate token and validate token methods

implement spring securiy to get the user details from the database. from there we have to user myuserdetails service in controller

add jwtauthenticatoin controller and add authenticate method there

handle two exceptions that are thown by controller : 	DisabledException ,BadCredentialsException 

then add a JwtRequestFilter to verify tocken for each and every request

create class JwtAuthenticationEntryPoint. This class will extend Spring's AuthenticationEntryPoint class and override its method commence. It rejects every unauthenticated request and send error code 401




