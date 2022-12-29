### Resolución
---

##### Se realizo el desarrollo de la api y se incluyeron los siguientes ítems:


###### Utilizar alguna librería que facilite el mantenimiento de los scripts DDL de base de datos.
>Se utilizo la librería para acceso a base de datos H2 y se creo el archivo schema.sql el cual se ejecuta en el startup del proyecto
<br>

###### Implementar una anotación personalizada que sirva para medir cuánto tarda en ejecutarse.
>Se implemento la anotación Traceable
<br>

###### Gestión centralizada de excepciones.
>Se implemento el BaseController para manejar las excepciones y además se creo e implementó la clase ExceptionAttributes
<br>

###### Test de integración.
>Se crearon 11 Test, se pueden ver en SuperheroesApplicationTests.
<br>

###### Presentar la aplicación dockerizada.
>Se implemento el archivo Dockerfile, luego ejecutar la dockerizacion para generar la imagen
<br>

###### Poder cachear peticiones.
>Se implemento Spring-boot-starter-cache sobre el metodo FindAll.
<br>

###### Documentación de la API.
>Se implemento Springdoc-openapi-ui y se documento.<br>
>Url de acceso local : http://localhost:8080/swagger-ui/index.html
<br>

###### Seguridad del API.
>Se implemento spring-boot-starter-security con JWT para la segurizacion.

<br><br>
###### Además se implementó:
---
* Maven
* Profiles
* Lombok
* Spring-Validation

<br><br>
###### Swagger
---
![](https://github.com/MartinG987/superheroes/blob/main/SwaggerSuperHeroes.jpg)


