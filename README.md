# Cinema Room REST Service
My implementation of [Hyperskill][1] [Cinema Room Manager][2] project.
In this project, I created a simple Spring REST service that help manage a small movie theatre. Handle HTTP requests in controllers, create services and respond with JSON objects.

## Requirements
- Java 11+
- IntelliJ IDEA / Netbeans / Eclipse

## Default port
8080

## Endpoints

### GET: /seats
Returns the information about the cinema room.

### POST: /purchase
Allow customers to purchase tickets

**Request body example**: 
```
{
"row": 9,
"column": 7
}
```

### POST: /return
Allow customers to refund their tickets

**Request body example**:
```
{
"token": "b65faeb5-b12c-47fc-871a-e1a9252c4533"
}
```

### POST: /stats
Show movie theater statistics by manager password

**Request param example**:
```
{
"password": "*********************"
}
```




[1]: https://hyperskill.org/
[2]: https://hyperskill.org/projects/189
