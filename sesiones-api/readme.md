# Sesiones-API
Encargado de la administración de las sesiones del ambiente

### Pasos para ejecutarlo
1. Ejecución de pruebas unitarias y generación del Jar:
```
./gradlew clean test bootJar
```
2. Creación de la imagen:
```
docker build --tag=sesiones-api:latest .
```
3. Ejecución
```
docker run -p8877:8877 -e EUREKA_URI=http://<MI_IP>:8761/eureka sesiones-api:latest
```
`<MI_IP>`= Se refiere a la ip, por ejemplo:
```
docker run -p8877:p8877 -e EUREKA_URI=http://192.168.0.7:8761/eureka sesiones-api:latest
```

### Detalle de Sesiones-API
Path base: `http://localhost:8877/sesiones`

Endpoint  | Detalle
------------- | -------------
[POST] /  | Genera una nueva sesion
[DELETE] /  | Cierra una sesion
[GET] /  | Retorna las sesiones activas del sistema
[GET] /{{sesionId}}  | Verifica si una sesion existe

Para más información de clic [aquí](http://localhost:8877/docs)

### Información adicional
Herramienta  | Versión
------------- | -------------
Spring boot  | 2.5.2
Unirest  | 1.4.9
Lombok  | 1.18.20
OpenApi   | 1.5.9
H2   | 1.4.200

### Notas
* Esta API al momento de su ejecución está levantando una base de datos en h2 en memoria.
