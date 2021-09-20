#Numeros-API
Encargado de la administración de los numeros por sesion del ambiente

###Pasos para ejecutarlo
1. Ejecución de pruebas unitarias y generación del Jar:
```
./gradlew clean test bootJar
```
2. Creación de la imagen:
```
docker build --tag=numeros-api:latest .
```
3. Ejecución
```
docker run -p8879:8879 -e EUREKA_URI=http://<MI_IP>:8761/eureka -e SESIONES_API=http://<MI_IP>:8877 numeros-api:latest
```
`<MI_IP>`= Se refiere a la ip, por ejemplo:
```
docker run -p8879:8879 -e EUREKA_URI=http://192.168.0.7:8761/eureka -e SESIONES_API=http://192.168.0.7:8877 numeros-api:latest
```

###Detalle de Numeros-API
Path base: `http://localhost:8879/numeros`

Endpoint  | Detalle | Cuerpo
------------- | ------------- | -------------
[GET] /{{sesionId}}  | Obtiene todos los numeros que pertenecen a una sesion | ---
[POST] /{{sesionId}}  | Agrega un numero a la sesion solicitada | {"numero": valor}
[PATCH] /{{sesionId}}  | Recibe un numero que reemplazará los numeros que pertenecen a una sesion | {"numero": valor}

Para más información de clic [aquí](http://localhost:8879/docs)

###Información adicional
Herramienta  | Versión
------------- | -------------
Spring boot  | 2.5.2
Unirest  | 1.4.9
Lombok  | 1.18.20
OpenApi   | 1.5.9
H2   | 1.4.200

###Notas
* Esta API al momento de su ejecución está levantando una base de datos en h2 en memoria.