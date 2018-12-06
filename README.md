[![HitCount](http://hits.dwyl.io/phantasmicmeans/spring-boot-restful-api-example.svg)](http://hits.dwyl.io/phantasmicmeans/spring-boot-restful-api-example)

# Spring Boot RESTful API - JPA Hibernate MySQL Example #
*by S.M.Lee(phantasmicmeans)*

RESTful API using Spring Boot, Swagger2, JPA hibernate and Mysql, One to Many, Many to One bidirectional mapping

&nbsp;

## Relation ## 

![image](https://user-images.githubusercontent.com/28649770/44622337-69c67a80-a8f1-11e8-99d7-34adb90779a3.png)


### Bidirectional Mapping ### 

* Project - Problem (One-To-Many)
* Problem - Project (Many-To-One)

* Problem - SubProblem (One-To-Many)
* SubProblem - Problem (Many-To-One)

&nbsp;

## Before we go, Check the domain class ## 

   **1. Problem.java(part of)**

```java
@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
private Set <Problem> problems = new HashSet<>();
/* Project <-> Problem One to Many bidirectional */
```

**2. Problem.java(part of)**

```java
@ManyToOne(cascade = CascadeType.REMOVE)
@JoinColumn(name = "code", referencedColumnName = "code", nullable = false)
private Project project; 
/* Problem <-> Project  Many to One bidirectional */

@OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
private Set<subProblem> subProblems = new HashSet<>();
//Problem <-> SubProblem  One to Many bidirectional
```

**3. SubProblem.java(part of)**

```java
@ManyToOne(cascade = CascadeType.REMOVE)
@JoinColumn(name = "pro_idx", referencedColumnName = "idx", nullable = false)
private Problem problem; 
/* Problem <-> Project Many to One bidirectional */
```

&nbsp;


## RESTful API Server ##

&nbsp;
**1. API Description for Project**

METHOD | PATH | DESCRIPTION 
------------|-----|------------
GET | /api/project/{code} | get Project-Problem-SubProblem with code
POST | /api/project | save Project (code will generate by constructor) 
DELETE | /api/project/{code} | delete Project with code
PUT | /api/project/{code} | update Project with code

&nbsp;
**2. API Description for Problem & SubProblem**

METHOD | PATH | DESCRIPTION 
------------|-----|------------
GET | /api/problem/{code} | get all Problem-Subproblem with code
POST | /api/problem/{code} | save Problem with code
DELETE | /api/problem/{code}/all | delete all Problem-Subproblem with code
POST | /api/subproblem | save Subproblem

&nbsp;

## Curl ## 

&nbsp;
**1. Curl for Project**

1. Get a Project with code
```bash
curl -X GET http://localhost:8080/problem/0gn547 
```

2. Save a Project with code 
```bash
curl -d '{"title":"first project"}' -H "Content-Type: application/json" -X POST http://localhost:8080/project
```

3. Delete a Project with code
```bash
curl -X DELETE http://localhost:8001/project/0gn547
```

4. Update a Project with code 
```bash
curl -X PUT -H "Content-Type: application/json; charset=utf-8" -d '{"title":"first-project-renewal"}' http://localhost:8080/project/hx6029
```
&nbsp;

**2. Curl for Problem & SubProblem**
&nbsp;

1. Get a Problem with code
```bash
curl -X GET http://localhost:8001/problem/0gn547 
```

2. Save a Problem with code
```bash
curl -d '{"title":"first problem"}' -H "Content-Type: application/json" -X POST http://localhost:8080/problem/hx6029
```

3. Delete a Problem-SubProblem with code
```bash
curl -X DELETE http://localhost:8001/problem/hx6029/all 
``` 
4. Save a SubProblem 
```bash
curl -d '{"content":"first-subproblem","pro_idx":1}' -H "Content-Type: application/json" -X POST http://localhost:8080/subproblem
```
&nbsp;

## Running the project with MySQL ##

append this at the end of application.yml
&nbsp;

```yml
spring:
    application:
      name: project-api
       
## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
    jpa: 
      properties:
        hibernate:
          dialect: org.hibernate.dialect.MySQL5InnoDBDialect
      hibernate:
        ddl-auto: update
        # Hibernate ddl auto (create, create-drop, validate, update)
      
    datasource:
      url: jdbc:mysql://{YOUR_MSQL_SERVER}:3306/{DATABASE NAME}?useSSL=false
      username: {YOUR_MYSQL_ID}
      password: {YOUR_MYSQL{PASSWORD}
      driver-class-name: com.mysql.jdbc.Driver
      hikari:
        maximum-pool-size: 2
```

&nbsp;


## Swagger ## 

You can use the Swagger API Documentation at http://{Your_Server}:{Port}/swagger-ui.html

![image](https://user-images.githubusercontent.com/28649770/44622453-8bc0fc80-a8f3-11e8-9223-b5a21717ba6d.png)

