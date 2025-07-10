# API VUELOS
## _API Rest para gestionar vuelos_

[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Esta es una API REST que gestiona los vuelos de un aeropuerto teniedo en cuenta a los pasajeros
, los aviones, las aerolineas y demas aeropuertos, utilizando operaciones CRUD y una base de datos relacional.

## Caracteristicas

- Implementacion de operaciones CRUD sobre los datos
- Conexion con una base de datos, en este caso MySQL
- Esta dividida en capas (web, dominio y persistencia)
- Implementa inyeccion de dependencias por constructor a traves de interfaces para evitar un alto acoplamiento
- Utiliza clases DTO para evitar operar directamente sobre las entidades de la base de datos
- Manejo de todas las excepciones a traves de Advice Exception Handler
- Documentada en DTOs y Controladores a traves de Swagger
- Validaciones en las clases DTO
- Mapeador para la conversion de DTOs a entities, y viceversa

## Tecnologias
Las tecnologias principales que se usaron para el proyecto fueron:

- Java 17 - lenguaje de programacion
- Intellij IDE - editor de codigo
- Spring Boot - framework de Java que simplifica la creación de aplicaciones basadas en Spring
- Spring Web - módulo de Spring Boot que facilita el desarrollo de aplicaciones web, especialmente servicios REST.
- Spring Data JPA - subproyecto de Spring que simplifica el acceso a bases de datos usando JPA
- Spring Validation / Jakarta Validation - sistema de validación de datos basado en anotaciones 
- Lombok - librería Java que reduce el código repetitivo
- MapStruct - libreria para convertir objetos dtos en entities y viceversa
- Swagger - conjunto de herramientas para documentar y probar APIs REST
- Maven - sistema de gestión de proyectos y dependencias para Java.
- MySQL - sistema de gestión de bases de datos relacional 

## Estructura principal del proyecto

```
...
└── 📁api_vuelos
    └── 📁configuration
        └── 📁audit
            └── AuditAwareImpl.java
            └── JpaAuditingConfig.java
        └── 📁doc
            └── SwaggerConfig.java
    └── 📁domain
        └── 📁dtos
            └── Airline.java
            └── AirportDestiny.java
            └── AirportOrigin.java
            └── Flight.java
            └── Passenger.java
            └── Plane.java
            └── 📁responses
                └── ErrorResponse.java
                └── MultiErrorResponse.java
                └── SuccessfulResponse.java
        └── 📁exceptions
            └── 📁airline_exception
                └── AirlineBadRequestException.java
                └── AirlineNotFoundException.java
            └── 📁airport_exception
                └── AirportBadRequestException.java
                └── AirportNotFoundException.java
            └── 📁flight_exception
                └── FlightBadRequestException.java
                └── FlightNotFoundException.java
            └── 📁passenger_exception
                └── PassengerBadRequestException.java
                └── PassengerNotFoundException.java
            └── 📁plane_exception
                └── PlaneBadRequestException.java
                └── PlaneNotFoundException.java
        └── 📁services
            └── IAirlineService.java
            └── IAirportDestinyService.java
            └── IAirportOriginService.java
            └── IFlightService.java
            └── 📁impl
                └── AirlineService.java
                └── AirportDestinyService.java
                └── AirportOriginService.java
                └── FlightService.java
                └── PassengerService.java
                └── PlaneService.java
            └── IPassengerService.java
            └── IPlaneService.java
    └── 📁persistence
        └── 📁entities
            └── AirlineEntity.java
            └── AirportDestinyEntity.java
            └── AirportOriginEntity.java
            └── FlightEntity.java
            └── PassengerEntity.java
            └── PlaneEntity.java
        └── 📁repositories
            └── 📁crud
                └── AirlineCrudRepository.java
                └── AirportDestinyCrudRepository.java
                └── AirportOriginCrudRepository.java
                └── FlightCrudRepository.java
                └── PassengerCrudRepository.java
                └── PlaneCrudRepository.java
            └── IAirlineRepository.java
            └── IAirportDestinyRepository.java
            └── IAirportOriginRepository.java
            └── IFlightRepository.java
            └── 📁impl
                └── AirlineRepository.java
                └── AirportDestinyRepository.java
                └── AirportOriginRepository.java
                └── FlightRepository.java
                └── PassengerRepository.java
                └── PlaneRepository.java
            └── IPassengerRepository.java
            └── IPlaneRepository.java
    └── 📁util
        └── 📁converters
            └── EndpointConverter.java
            └── IdConverter.java
        └── 📁mappers
            └── AirlineMapper.java
            └── AirportMapper.java
            └── FlightMapper.java
            └── PassengerMapper.java
            └── PlaneMapper.java
    └── 📁web
        └── 📁advice
            └── AdviceExceptionHandler.java
        └── 📁controllers
            └── AirlineController.java
            └── AirportDestinyController.java
            └── AirportOriginController.java
            └── FlightController.java
            └── PassengerController.java
            └── PlaneController.java
    └── ApiVuelosApplication.java
```

## Endpoints

Usando esta url como base:
http://localhost:8080/vuelos/api/v1

AIRLINE (aerolinea)
| Metodo     | Endpoint    | Descripcion   |
| :---       |    :----:   |          ---: |
| GET        | /airlines/all       | Obtiene todas las aerolineas   |
| GET        | /airlines/1        | Obtiene una aerolinea por su ID      |
| GET        | /airlines?name=aerolinea-x       | Obtiene una aerolinea por su nombre   |
| POST       | /airlines/create        | Crea una aerolinea nueva      |
| PUT        | /airlines/update/1       | Actualiza una aerolinea a traves de su ID   |
| DELETE     | /airlines/delete/1        | Elimina una aerolinea por su ID      |

DESTINATION AIRPORT (aeropuerto de destino)
| Metodo     | Endpoint    | Descripcion   |
| :---       |    :----:   |          ---: |
| GET        | /airports/destinies/all      | Obtiene todos los aeropuertos de destino   |
| GET        | /airports/destiny/2       | Obtiene un aeropuerto de destino por su ID   |
| GET        | /airports/destiny?name=aeropuerto-x        | Obtiene un aeropuerto de destino por su nombre      |
| GET        | /airports/destinies/all/country?country=argentina       | Obtiene todos los aeropuertos de destino de un determinado pais   |
| POST       | /airports/destiny/create        | Crea un nuevo aeropuerto de destino      |
| PUT        | /airports/destiny/update/1       | Actualiza un aeropuerto de destino a traves de su ID   |
| DELETE     | /airports/destiny/delete/2        | Elimina un aeropuerto de destino por su ID      |

ORIGIN AIRPORT (aeropuerto de origen)
| Metodo     | Endpoint    | Descripcion   |
| :---       |    :----:   |          ---: |
| GET        | /airports/origins/all      | Obtiene todos los aeropuertos de origen   |
| GET        | /airports/origin/2       | Obtiene un aeropuerto de origen por su ID   |
| GET        | /airports/origin?name=aeropuerto-x        | Obtiene un aeropuerto de origen por su nombre      |
| GET        | /airports/origins/all/country?country=argentina       | Obtiene todos los aeropuertos de origen de un determinado pais   |
| POST       | /airports/origin/create        | Crea un nuevo aeropuerto de origen      |
| PUT        | /airports/origin/update/1       | Actualiza un aeropuerto de origen a traves de su ID   |
| DELETE     | /airports/origin/delete/2        | Elimina un aeropuerto de origen por su ID      |

PLANE (avion)
| Metodo     | Endpoint    | Descripcion   |
| :---       |    :----:   |          ---: |
| GET        | /planes/all       | Obtiene todos los aviones   |
| GET        | /plane/1        | Obtiene un avion por su ID      |
| GET        | /planes?model=avion-1       | Obtiene un avion por su modelo   |
| POST       | /planes/create        | Crea un nuevo avion      |
| PUT        | /planes/update/1       | Actualiza un avion a traves de su ID   |
| DELETE     | /planes/delete/1        | Elimina un avion por su ID      |

PASSENGER (pasajero)
| Metodo     | Endpoint    | Descripcion   |
| :---       |    :----:   |          ---: |
| GET        | /passengers/all       | Obtiene todos los pasajeros   |
| GET        | /passengers/1       | Obtiene un pasajero por su ID   |
| GET        | /passengers?firstname=juan&lastname=perez        | Obtiene un pasajero por su nombre y apellido      |
| GET        | /passengers?passport=a123456       | Obtiene un pasajero por su numero de pasaporte   |
| POST       | /passengers/create        | Crea un nuevo pasajero      |
| PUT        | /passengers/update/1       | Actualiza un pasajero a traves de su ID   |
| DELETE     | /passengers/delete/1        | Elimina un pasajero por su ID      |

FLIGHT (vuelo)
| Metodo     | Endpoint    | Descripcion   |
| :---       |    :----:   |          ---: |
| GET        | /flights/all       | Obtiene todos los vuelos   |
| GET        | /flights/1        | Obtiene un vuelo por su ID      |
| GET        | /flights/1/all-passengers       | Obtiene todos los pasajeros de un vuelo determinado   |
| POST       | /flights/create        | Crea un nuevo vuelo      |
| PUT        | /flights/update/1       | Actualiza un vuelo a traves de su ID   |
| DELETE     | /flights/delete/1        | Elimina un vuelo por su ID      |


## Estructura de la base de datos (en edicion)
![Estructura de la Base de Datos](/src/main/resources/img/estructura_bd.jpg)

## Pasos a seguir
- Agregar seguridad sobre los endpoint creando diferentes roles para su manipulacion con Spring Security
- Personalizar y crear una division clara en los solicitudes y respuestas
- Agregar paginacion para obtener una cantidad de datos especifica

y como siempre en el desarrollo de software hay que seguir refactorizando, mejorando y agregando nuevas funciones a la aplicacion.





