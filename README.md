# JwtAuthentication

This Repository contains code for Authentication And Authorization using JWT( JSON WEB TOKEN ).

## STORAGE
Here in this application i am using H2 database and initially i'm adding 3 records using CommandLineRunner in main class.

## Authentication
At the time of running this application it will automatically insert 3 records of data because of "CommandLineRunner" and here i'm saving passwords using "BCryptPasswordEncoder"

## Authorization
Here we have 3 authorities -> principal ,teacher ,student
Role Principal Authority can access -> /principal, /teacher, /student.
Role Teacher Authority can access -> /teacher, /student.
Role Student Authority can access -> /student.

## Token Expiry Time
After every 30 sec Jwt Token will expire.
