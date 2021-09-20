#Registrador-API
Servidor de Eureka para registrar las apis 

###Pasos para ejecutarlo
1. Generación del Jar:
```
./gradlew clean bootJar
```
2. Creación de la imagen:
```
docker build --tag=registrador-api:latest .
```
3. Ejecución
```
docker run -p 8761 registrador-api:latest
```

###Acceso
[Path base](http://localhost:8761)

###Información adicional
Herramienta  | Versión
------------- | -------------
Spring boot  | 2.5.2
Eureka Server   | 3.0.3